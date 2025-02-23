package com.stamp_iot_project.entity;

import lombok.Data;
import jakarta.persistence.*;

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