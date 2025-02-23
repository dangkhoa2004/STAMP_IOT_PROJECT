package com.stamp_iot_project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @Column(nullable = false)
    private Integer quantity = 0;

    // Lưu ý: Để cập nhật timestamp tự động, có thể dùng @PrePersist và @PreUpdate hoặc cấu hình ở DB.
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;
}