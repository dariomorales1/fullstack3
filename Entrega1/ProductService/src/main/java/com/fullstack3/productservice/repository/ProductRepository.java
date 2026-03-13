package com.fullstack3.productservice.repository;

import com.fullstack3.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findById(long id);
}
