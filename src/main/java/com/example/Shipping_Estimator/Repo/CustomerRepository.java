package com.example.Shipping_Estimator.Repo;

import com.example.Shipping_Estimator.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for performing CRUD operations on Customer entity.
 * Provides built-in JPA methods
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}