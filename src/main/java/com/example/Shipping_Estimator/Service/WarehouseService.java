package com.example.Shipping_Estimator.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Shipping_Estimator.Model.Product;
import com.example.Shipping_Estimator.Model.Seller;
import com.example.Shipping_Estimator.Model.Warehouse;
import com.example.Shipping_Estimator.Repo.ProductRepository;
import com.example.Shipping_Estimator.Repo.SellerRepository;
import com.example.Shipping_Estimator.Repo.WarehouseRepository;
import com.example.Shipping_Estimator.util.DistanceUtil;

import lombok.RequiredArgsConstructor;

/**
 * Service responsible for warehouse-related operations.
 * 
 * Core Responsibility:
 * - Determine the nearest warehouse for a given seller.
 * - Validate that product belongs to the seller.
 */
@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final SellerRepository sellerRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;

    /**
     * Finds the nearest warehouse for the given seller and product.
     *
     * Steps:
     * 1. Validate seller exists.
     * 2. Validate product exists.
     * 3. Ensure product belongs to seller.
     * 4. Calculate distance from seller to all warehouses.
     * 5. Return the warehouse with minimum distance.
     *
     * @param sellerId  ID of seller
     * @param productId ID of product
     * @return nearest Warehouse entity
     */
    public Warehouse findNearestWarehouse(Long sellerId, Long productId) {

        // ---------- Step 1: Fetch Seller ----------
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        // ---------- Step 2: Fetch Product ----------
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // ---------- Step 3: Validate Product Ownership ----------
        if (!product.getSeller().getId().equals(sellerId)) {
            throw new RuntimeException("Product does not belong to seller");
        }

        // ---------- Step 4: Fetch All Warehouses ----------
        List<Warehouse> warehouses = warehouseRepository.findAll();

        if (warehouses.isEmpty()) {
            throw new RuntimeException("No warehouses available");
        }

        // ---------- Step 5: Find Minimum Distance ----------
        Warehouse nearestWarehouse = null;
        double minDistance = Double.MAX_VALUE;

        for (Warehouse warehouse : warehouses) {

            // Calculate real-world distance using Haversine formula
            double distance = DistanceUtil.calculateDistance(
                    seller.getLatitude(),
                    seller.getLongitude(),
                    warehouse.getLatitude(),
                    warehouse.getLongitude()
            );

            // Update minimum distance warehouse
            if (distance < minDistance) {
                minDistance = distance;
                nearestWarehouse = warehouse;
            }
        }

        // ---------- Step 6: Return Result ----------
        return nearestWarehouse;
    }
}