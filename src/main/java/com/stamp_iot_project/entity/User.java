package com.stamp_iot_project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "`User`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(nullable = false, length = 255, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(length = 50)
    private String status = "Active";

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}