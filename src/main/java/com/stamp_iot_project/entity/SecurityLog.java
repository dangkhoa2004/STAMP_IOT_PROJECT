package com.stamp_iot_project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "SecurityLog")
public class SecurityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    @Column(length = 100)
    private String eventType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(length = 255)
    private String location;
}