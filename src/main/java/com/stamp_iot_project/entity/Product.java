package com.stamp_iot_project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 100)
    private String brand;

    @Column(length = 100)
    private String category;

    @Column(columnDefinition = "TEXT")
    private String specification;

    private BigDecimal price;

    @Column(length = 255)
    private String imageUrl;
}
