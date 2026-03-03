package com.example.Shipping_Estimator.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Shipping_Estimator.Model.Product;

/**
 * Repository for Product entity.
 * Provides standard CRUD operations
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}