package com.stamp_iot_project.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @Column(nullable = false, length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(length = 255)
    private String contactInfo;

    @Temporal(TemporalType.DATE)
    private Date hireDate;

    private BigDecimal salary;
}
