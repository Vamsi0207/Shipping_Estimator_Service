package com.example.Shipping_Estimator.Service.Strategy;

public interface DeliveryChargeStrategy {
    double applyCharge(double baseCharge, double weight);
}
