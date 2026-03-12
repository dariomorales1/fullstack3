package com.fullstack3.productservice.controller;

import com.fullstack3.productservice.model.TestProduct;
import com.fullstack3.productservice.repository.TestProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final TestProductRepository repository;

    public ProductController(TestProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String testDB() {

        TestProduct product = new TestProduct();
        product.setName("conection ok");

        repository.save(product);

        return "guardado en db";
    }

}
