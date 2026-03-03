package com.example.Shipping_Estimator.Model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a warehouse in the system.
 * Sellers drop products at the nearest warehouse,
 * and shipments are dispatched from there to customers.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {

    /**
     * Unique identifier for the warehouse.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Warehouse name (e.g., BLR_Warehouse).
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Latitude of warehouse location.
     * Used for distance calculation.
     */
    @Column(nullable = false)
    private double latitude;

    /**
     * Longitude of warehouse location.
     * Used for distance calculation.
     */
    @Column(nullable = false)
    private double longitude;
}