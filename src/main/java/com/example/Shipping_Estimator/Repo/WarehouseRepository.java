package com.example.Shipping_Estimator.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Shipping_Estimator.Model.Warehouse;

/**
 * Repository for Warehouse entity.
 * Provides standard CRUD operations through Spring Data JPA.
 */
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

}