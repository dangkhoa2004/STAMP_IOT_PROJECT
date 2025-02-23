package com.stamp_iot_project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "ProductionOrder")
public class ProductionOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(length = 50)
    private String status;
}
