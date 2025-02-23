package com.stamp_iot_project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Column(nullable = false, length = 100)
    private String roleName;

    @Column(columnDefinition = "TEXT")
    private String description;
}