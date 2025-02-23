package com.stamp_iot_project.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ProductionStep")
public class ProductionStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stepId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private ProductionOrder productionOrder;

    @Column(nullable = false)
    private Integer stepNumber;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(length = 50)
    private String status;
}