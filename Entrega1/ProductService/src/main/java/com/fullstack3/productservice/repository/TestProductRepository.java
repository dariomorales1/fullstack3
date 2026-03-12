package com.fullstack3.productservice.repository;

import com.fullstack3.productservice.model.TestProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestProductRepository extends JpaRepository<TestProduct, Long> {
}
