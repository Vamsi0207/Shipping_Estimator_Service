package com.example.Shipping_Estimator.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Shipping_Estimator.Model.Seller;

/**
 * Repository for Seller entity.
 * Provides built-in CRUD operations using Spring Data JPA.
 */
public interface SellerRepository extends JpaRepository<Seller, Long> {

}