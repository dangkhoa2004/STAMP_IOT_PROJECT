package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Report;
import com.stamp_iot_project.repository.ReportRepository;
import com.stamp_iot_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private UserRepository userRepository;

    public ApiResponse<List<Report>> getAllReports() {
        List<Report> list = reportRepository.findAll();
        return new ApiResponse<>("success", "Fetched reports thành công", list, 200, new Date(), null);
    }

    public ApiResponse<Report> getReportById(Integer id) {
        Optional<Report> opt = reportRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Fetched report thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Report không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Report> createReport(Report report, Integer createdById) {
        if (createdById != null) {
            userRepository.findById(createdById).ifPresent(report::setCreatedBy);
        }
        Report saved = reportRepository.save(report);
        return new ApiResponse<>("success", "Report created", saved, 201, new Date(), null);
    }

    public ApiResponse<Report> updateReport(Integer id, Report report) {
        Optional<Report> opt = reportRepository.findById(id);
        if (opt.isPresent()) {
            Report existing = opt.get();
            existing.setReportName(report.getReportName());
            existing.setReportType(report.getReportType());
            existing.setParameters(report.getParameters());
            existing.setSummary(report.getSummary());
            existing.setFileUrl(report.getFileUrl());
            if (report.getCreatedBy() != null && report.getCreatedBy().getUserId() != null) {
                userRepository.findById(report.getCreatedBy().getUserId()).ifPresent(existing::setCreatedBy);
            }
            Report updated = reportRepository.save(existing);
            return new ApiResponse<>("success", "Report updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Report không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteReport(Integer id) {
        reportRepository.deleteById(id);
        return new ApiResponse<>("success", "Report deleted", null, 200, new Date(), null);
    }
}

