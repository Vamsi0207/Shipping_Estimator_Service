package com.example.Shipping_Estimator.Service.Strategy;

public class ExpressDeliveryStrategy implements DeliveryChargeStrategy {

    @Override
    public double applyCharge(double baseCharge, double weight) {
        return baseCharge + 10 + (1.2 * weight);
    }
}