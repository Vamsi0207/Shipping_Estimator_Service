package com.example.Shipping_Estimator.Service.Strategy;

public class StandardDeliveryStrategy implements DeliveryChargeStrategy {

    @Override
    public double applyCharge(double baseCharge, double weight) {
        return baseCharge + 10;
    }
}