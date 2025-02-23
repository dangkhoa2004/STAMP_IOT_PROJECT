package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Employee;
import com.stamp_iot_project.entity.SecurityLog;
import com.stamp_iot_project.repository.EmployeeRepository;
import com.stamp_iot_project.repository.SecurityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SecurityLogService {

    @Autowired
    private SecurityLogRepository logRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public ApiResponse<List<SecurityLog>> getAllLogs() {
        List<SecurityLog> list = logRepository.findAll();
        return new ApiResponse<>("success", "Fetched security logs thành công", list, 200, new Date(), null);
    }

    public ApiResponse<SecurityLog> getLogById(Integer id) {
        Optional<SecurityLog> opt = logRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Fetched security log thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Security log không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<SecurityLog> createLog(SecurityLog log, Integer employeeId) {
        if (employeeId != null) {
            employeeRepository.findById(employeeId).ifPresent(log::setEmployee);
        }
        SecurityLog saved = logRepository.save(log);
        return new ApiResponse<>("success", "Security log created", saved, 201, new Date(), null);
    }

    public ApiResponse<SecurityLog> updateLog(Integer id, SecurityLog log) {
        Optional<SecurityLog> opt = logRepository.findById(id);
        if (opt.isPresent()) {
            SecurityLog existing = opt.get();
            existing.setEventType(log.getEventType());
            existing.setEventDate(log.getEventDate());
            existing.setDescription(log.getDescription());
            existing.setLocation(log.getLocation());
            SecurityLog updated = logRepository.save(existing);
            return new ApiResponse<>("success", "Security log updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Security log không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteLog(Integer id) {
        logRepository.deleteById(id);
        return new ApiResponse<>("success", "Security log deleted", null, 200, new Date(), null);
    }
}
