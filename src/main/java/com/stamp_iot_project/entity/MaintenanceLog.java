package com.stamp_iot_project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "MaintenanceLog")
public class MaintenanceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maintenanceId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date maintenanceDate;

    @Column(length = 100)
    private String maintenanceType;

    @ManyToOne
    @JoinColumn(name = "performed_by")
    private Employee performedBy;

    @Column(columnDefinition = "TEXT")
    private String maintenanceDescription;

    private BigDecimal cost;

    @Temporal(TemporalType.DATE)
    private Date nextScheduledDate;

    @Column(length = 50)
    private String status;
}
