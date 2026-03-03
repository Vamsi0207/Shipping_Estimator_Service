package com.example.Shipping_Estimator.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.example.Shipping_Estimator.Model.*;
import com.example.Shipping_Estimator.Repo.*;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;

    /**
     * Loads sample data into H2 database on application startup.
     * Prevents duplicate inserts if data already exists.
     */
    @Override
    @Transactional
    public void run(String... args) {

        // Prevent duplicate loading on restart
        if (customerRepository.count() > 0) {
            System.out.println("Data already exists. Skipping data load.");
            return;
        }

        loadCustomers();
        loadSellersAndProducts();
        loadWarehouses();

        System.out.println("Sample Data Loaded Successfully");
    }

    private void loadCustomers() {

        Customer c1 = Customer.builder()
                .name("Shree Kirana Store")
                .phoneNumber("9847000000")
                .latitude(11.232)
                .longitude(23.445495)
                .build();

        Customer c2 = Customer.builder()
                .name("Andheri Mini Mart")
                .phoneNumber("9101000000")
                .latitude(17.232)
                .longitude(33.445495)
                .build();

        customerRepository.save(c1);
        customerRepository.save(c2);
    }

    private void loadSellersAndProducts() {

        Seller s1 = Seller.builder()
                .name("Nestle Seller")
                .latitude(12.0)
                .longitude(25.0)
                .build();

        Seller s2 = Seller.builder()
                .name("Rice Seller")
                .latitude(18.0)
                .longitude(30.0)
                .build();

        sellerRepository.save(s1);
        sellerRepository.save(s2);

        Product p1 = Product.builder()
                .name("Maggie 500g Packet")
                .weight(0.5)
                .length(10)
                .width(10)
                .height(10)
                .seller(s1)
                .build();

        Product p2 = Product.builder()
                .name("Rice Bag 10Kg")
                .weight(10)
                .length(100)
                .width(80)
                .height(50)
                .seller(s2)
                .build();

        productRepository.save(p1);
        productRepository.save(p2);
    }

    private void loadWarehouses() {

        Warehouse w1 = Warehouse.builder()
                .name("BLR_Warehouse")
                .latitude(12.99999)
                .longitude(37.923273)
                .build();

        Warehouse w2 = Warehouse.builder()
                .name("MUMB_Warehouse")
                .latitude(11.99999)
                .longitude(27.923273)
                .build();

        warehouseRepository.save(w1);
        warehouseRepository.save(w2);
    }
}