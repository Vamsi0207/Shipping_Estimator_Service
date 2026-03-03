package com.example.Shipping_Estimator.Model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a Kirana store customer in the system.
 * Each customer has a geographic location used for
 * distance calculation during shipping.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    /**
     * Unique identifier for the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the Kirana store.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Contact number of the store owner.
     */
    @Column(nullable = false, length = 15)
    private String phoneNumber;

    /**
     * Latitude of the store location.
     * Used in Haversine distance calculation.
     */
    @Column(nullable = false)
    private double latitude;

    /**
     * Longitude of the store location.
     * Used in Haversine distance calculation.
     */
    @Column(nullable = false)
    private double longitude;
}