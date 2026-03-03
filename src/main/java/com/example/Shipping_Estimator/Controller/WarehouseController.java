package com.example.Shipping_Estimator.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shipping_Estimator.Model.Warehouse;
import com.example.Shipping_Estimator.Service.WarehouseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/warehouse")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    /**
     * API:
     * GET /api/v1/warehouse/nearest?sellerId=1&productId=1
     *
     * Returns nearest warehouse for given seller and product
     */
    @GetMapping("/nearest")
    public Warehouse getNearestWarehouse(
            @RequestParam Long sellerId,
            @RequestParam Long productId) {

        return warehouseService.findNearestWarehouse(sellerId, productId);
    }
}