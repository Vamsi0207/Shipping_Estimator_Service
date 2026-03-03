package com.example.Shipping_Estimator.Model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a seller in the marketplace.
 * Sellers drop their products at the nearest warehouse
 * before delivery to customers.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seller {

    /**
     * Unique identifier for the seller.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Seller business name.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Latitude of seller location.
     * Used to determine nearest warehouse.
     */
    @Column(nullable = false)
    private double latitude;

    /**
     * Longitude of seller location.
     * Used to determine nearest warehouse.
     */
    @Column(nullable = false)
    private double longitude;
}