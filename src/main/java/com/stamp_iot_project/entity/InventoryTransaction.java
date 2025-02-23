package com.stamp_iot_project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "InventoryTransaction")
public class InventoryTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Column(length = 50, nullable = false)
    private String transactionType;

    @Column(nullable = false)
    private Integer quantity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date transactionDate;

    @Column(columnDefinition = "TEXT")
    private String description;
}
