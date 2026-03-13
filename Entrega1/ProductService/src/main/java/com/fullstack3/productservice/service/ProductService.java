package com.fullstack3.productservice.service;

import com.fullstack3.productservice.model.Product;
import com.fullstack3.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.delete(productRepository.findById(id));
    }

    public void updateProduct(Product product, int id) {
        Product existing = productRepository.findById(id);
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        productRepository.save(existing);
    }

}
