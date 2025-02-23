package com.stamp_iot_project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "QualityControl")
public class QualityControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer qcId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; // Có thể null nếu không cần kiểm tra sản phẩm cụ thể

    @ManyToOne
    @JoinColumn(name = "production_order_id")
    private ProductionOrder productionOrder;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date inspectionDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "inspector_id")
    private Employee inspector;

    @Column(length = 50)
    private String result;

    @Column(columnDefinition = "TEXT")
    private String notes;
}
