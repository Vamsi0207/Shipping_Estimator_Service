package com.example.Shipping_Estimator.Controller;

import org.springframework.web.bind.annotation.*;

import com.example.Shipping_Estimator.DTO.ShippingRequest;
import com.example.Shipping_Estimator.DTO.ShippingResponse;
import com.example.Shipping_Estimator.Service.ShippingService;

import lombok.RequiredArgsConstructor;

/**
 * REST Controller for Shipping related APIs.
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    /**
     * API:
     * GET /api/v1/shipping-charge
     *
     * Calculates shipping charge from a specific warehouse.
     */
    @GetMapping("/shipping-charge")
    public double getShippingCharge(
            @RequestParam Long warehouseId,
            @RequestParam Long customerId,
            @RequestParam Long productId,
            @RequestParam String deliverySpeed) {

        return shippingService.calculateShippingCharge(
                warehouseId,
                customerId,
                productId,
                deliverySpeed
        );
    }

    /**
     * API:
     * POST /api/v1/shipping-charge/calculate
     *
     * Combines:
     * 1. Nearest warehouse lookup
     * 2. Shipping calculation
     */
    @PostMapping("/shipping-charge/calculate")
    public ShippingResponse calculateShipping(
            @RequestBody ShippingRequest request) {

        return shippingService.calculateShippingForSellerCustomer(
                request.getSellerId(),
                request.getCustomerId(),
                request.getProductId(),
                request.getDeliverySpeed()
        );
    }
}