package com.stamp_iot_project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "RawMaterial")
public class RawMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer materialId;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(length = 50)
    private String unit;

    private Integer currentStock;

    private Integer reorderLevel;
}
