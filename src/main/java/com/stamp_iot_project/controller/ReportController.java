package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Report;
import com.stamp_iot_project.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public ApiResponse<List<Report>> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public ApiResponse<Report> getReportById(@PathVariable Integer id) {
        return reportService.getReportById(id);
    }

    // Khi tạo Report, nếu có createdById
    @PostMapping("/{createdById}")
    public ApiResponse<Report> createReport(@RequestBody Report report,
                                            @PathVariable(required = false) Integer createdById) {
        return reportService.createReport(report, createdById);
    }

    @PutMapping("/{id}")
    public ApiResponse<Report> updateReport(@PathVariable Integer id, @RequestBody Report report) {
        return reportService.updateReport(id, report);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteReport(@PathVariable Integer id) {
        return reportService.deleteReport(id);
    }
}

