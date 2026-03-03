package com.example.Shipping_Estimator.Service;

import org.springframework.stereotype.Service;

import com.example.Shipping_Estimator.DTO.ShippingResponse;
import com.example.Shipping_Estimator.Exception.ResourceNotFoundException;
import com.example.Shipping_Estimator.Model.Customer;
import com.example.Shipping_Estimator.Model.Product;
import com.example.Shipping_Estimator.Model.Warehouse;
import com.example.Shipping_Estimator.Repo.CustomerRepository;
import com.example.Shipping_Estimator.Repo.ProductRepository;
import com.example.Shipping_Estimator.Repo.WarehouseRepository;
import com.example.Shipping_Estimator.Service.Strategy.DeliveryChargeStrategy;
import com.example.Shipping_Estimator.Service.Strategy.ExpressDeliveryStrategy;
import com.example.Shipping_Estimator.Service.Strategy.StandardDeliveryStrategy;
import com.example.Shipping_Estimator.util.DistanceUtil;

import lombok.RequiredArgsConstructor;

/**
 * Service responsible for calculating shipping charges.
 */
@Service
@RequiredArgsConstructor
public class ShippingService {

    private final WarehouseRepository warehouseRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final WarehouseService warehouseService;

    /**
     * Calculates shipping charge from a specific warehouse to a customer.
     */
    public double calculateShippingCharge(
            Long warehouseId,
            Long customerId,
            Long productId,
            String deliverySpeed) {

        // ---------- Fetch Required Entities ----------
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // ---------- Step 1: Calculate Distance ----------
        double distance = DistanceUtil.calculateDistance(
                warehouse.getLatitude(),
                warehouse.getLongitude(),
                customer.getLatitude(),
                customer.getLongitude()
        );

        // ---------- Step 2: Determine Transport Rate ----------
        double ratePerKmPerKg;

        if (distance <= 100) {
            ratePerKmPerKg = 3;
        } else if (distance <= 500) {
            ratePerKmPerKg = 2;
        } else {
            ratePerKmPerKg = 1;
        }

        // ---------- Step 3: Base Shipping ----------
        double baseCharge =
                distance * ratePerKmPerKg * product.getWeight();

        // ---------- Step 4: Apply Delivery Strategy ----------
        DeliveryChargeStrategy strategy;

        if ("express".equalsIgnoreCase(deliverySpeed)) {
            strategy = new ExpressDeliveryStrategy();
        } else {
            strategy = new StandardDeliveryStrategy();
        }

        return strategy.applyCharge(baseCharge, product.getWeight());
    }

    /**
     * Combined method:
     * 1. Finds nearest warehouse for seller
     * 2. Calculates shipping charge
     * 3. Returns structured response
     */
    public ShippingResponse calculateShippingForSellerCustomer(
            Long sellerId,
            Long customerId,
            Long productId,
            String deliverySpeed) {

        // ---------- Step 1: Find Nearest Warehouse ----------
        Warehouse nearestWarehouse =
                warehouseService.findNearestWarehouse(sellerId, productId);

        // ---------- Step 2: Calculate Shipping ----------
        double charge = calculateShippingCharge(
                nearestWarehouse.getId(),
                customerId,
                productId,
                deliverySpeed
        );

        // ---------- Step 3: Build Response ----------
        return new ShippingResponse(
                charge,
                new ShippingResponse.NearestWarehouse(
                        nearestWarehouse.getId(),
                        new ShippingResponse.Location(
                                nearestWarehouse.getLatitude(),
                                nearestWarehouse.getLongitude()
                        )
                )
        );
    }
}