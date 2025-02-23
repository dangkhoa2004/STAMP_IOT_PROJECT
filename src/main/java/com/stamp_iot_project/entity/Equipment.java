package com.stamp_iot_project.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer equipmentId;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 100)
    private String type;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Temporal(TemporalType.DATE)
    private Date purchaseDate;

    @Column(length = 255)
    private String maintenanceSchedule;

    @Column(length = 50)
    private String status;
}
