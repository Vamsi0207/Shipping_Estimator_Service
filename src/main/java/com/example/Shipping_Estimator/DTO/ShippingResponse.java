package com.example.Shipping_Estimator.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShippingResponse {
    
    private double shippingCharge;
    private NearestWarehouse nearestWarehouse;

    @Data
    @AllArgsConstructor
    public static class NearestWarehouse {
        private Long warehouseId;
        private Location warehouseLocation;
    }

    @Data
    @AllArgsConstructor
    public static class Location {
        private double lat;
        private double lng;
    }

}
