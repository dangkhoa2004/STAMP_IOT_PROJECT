package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.SecurityLog;
import com.stamp_iot_project.service.SecurityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/security-logs")
public class SecurityLogController {

    @Autowired
    private SecurityLogService logService;

    @GetMapping
    public ApiResponse<List<SecurityLog>> getAllLogs() {
        return logService.getAllLogs();
    }

    @GetMapping("/{id}")
    public ApiResponse<SecurityLog> getLogById(@PathVariable Integer id) {
        return logService.getLogById(id);
    }

    // Khi tạo log, nếu có employeeId
    @PostMapping("/{employeeId}")
    public ApiResponse<SecurityLog> createLog(@RequestBody SecurityLog log,
                                              @PathVariable(required = false) Integer employeeId) {
        return logService.createLog(log, employeeId);
    }

    @PutMapping("/{id}")
    public ApiResponse<SecurityLog> updateLog(@PathVariable Integer id, @RequestBody SecurityLog log) {
        return logService.updateLog(id, log);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteLog(@PathVariable Integer id) {
        return logService.deleteLog(id);
    }
}

