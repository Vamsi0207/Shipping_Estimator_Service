package com.example.Shipping_Estimator.Model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a product sold by a seller in the marketplace.
 * Product attributes like weight and dimensions are used
 * for calculating shipping charges.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    /**
     * Unique identifier for the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the product (e.g., Rice Bag 10Kg).
     */
    @Column(nullable = false)
    private String name;

    /**
     * Weight of the product in kilograms.
     * Used in shipping cost calculation.
     */
    @Column(nullable = false)
    private double weight;

    /**
     * Physical dimensions of the product in centimeters.
     * (Length × Width × Height)
     */
    @Column(nullable = false)
    private double length;

    @Column(nullable = false)
    private double width;

    @Column(nullable = false)
    private double height;

    /**
     * Seller who owns this product.
     * Many products can belong to one seller.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;
}