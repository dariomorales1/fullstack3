package com.fullstack3.productservice.controller;

import com.fullstack3.productservice.controller.response.MessageResponse;
import com.fullstack3.productservice.model.Product;
import com.fullstack3.productservice.model.TestProduct;
import com.fullstack3.productservice.repository.TestProductRepository;
import com.fullstack3.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/health")
    public ResponseEntity<MessageResponse> health() {
        return ResponseEntity.ok(new MessageResponse("OK"));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProducts()
                .stream()
                .sorted(Comparator.comparing(Product::getId))
                .collect(Collectors.toList());
        return products.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable int productId) {
        Product existingProduct = productService.getProductById(productId);
        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("Product not found"));
        }

        return ResponseEntity.ok(existingProduct);
    }

    @PostMapping("/")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            Product existingProduct = productService.getProductById(product.getId());
            if (existingProduct != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new MessageResponse("Product already exists"));
            } else {
                productService.addProduct(product);
                return ResponseEntity.ok(new MessageResponse("Product added"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Internal Server Error"));
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable int productId) {

        try {
            Product existingProduct = productService.getProductById(productId);
            if (existingProduct == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(new MessageResponse("Product not found"));
            }
            productService.deleteProduct(productId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new MessageResponse("Product deleted"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error deleting product: " + e.getMessage()));
        }
    }
}
