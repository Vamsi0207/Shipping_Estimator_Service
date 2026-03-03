package com.example.Shipping_Estimator.DTO;

import lombok.Data;

@Data
public class ShippingRequest {
    
    private Long sellerId;
    private Long customerId;
    private Long productId;
    private String deliverySpeed;

}
