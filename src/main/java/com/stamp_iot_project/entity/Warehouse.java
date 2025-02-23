package com.stamp_iot_project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer warehouseId;

    @Column(nullable = false, length = 255)
    private String location;

    private Integer capacity;

    @Column(columnDefinition = "TEXT")
    private String description;
}
