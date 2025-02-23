package com.stamp_iot_project.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportId;

    @Column(nullable = false, length = 255)
    private String reportName;

    @Column(length = 100)
    private String reportType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(columnDefinition = "TEXT")
    private String parameters; // Có thể lưu dưới dạng JSON

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column(length = 255)
    private String fileUrl;
}