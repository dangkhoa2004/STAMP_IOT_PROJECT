package com.stamp_iot_project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplierId;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 255)
    private String contactInfo;
}
