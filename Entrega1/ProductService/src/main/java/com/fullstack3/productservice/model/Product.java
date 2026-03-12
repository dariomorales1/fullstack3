package com.fullstack3.productservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table (name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique=true)
    private String name;

    @Column(nullable=false)
    private double price;

    @Column(nullable=false)
    private String description;
}
