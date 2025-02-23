package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.MaintenanceLog;
import com.stamp_iot_project.service.MaintenanceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance-logs")
public class MaintenanceLogController {

    @Autowired
    private MaintenanceLogService maintenanceLogService;

    @GetMapping
    public ApiResponse<List<MaintenanceLog>> getAllLogs() {
        return maintenanceLogService.getAllLogs();
    }

    @GetMapping("/{id}")
    public ApiResponse<MaintenanceLog> getLogById(@PathVariable Integer id) {
        return maintenanceLogService.getLogById(id);
    }

    // Khi tạo MaintenanceLog, truyền equipmentId và performedById nếu có
    @PostMapping("/{equipmentId}/{performedById}")
    public ApiResponse<MaintenanceLog> createLog(@RequestBody MaintenanceLog log,
                                                 @PathVariable Integer equipmentId,
                                                 @PathVariable(required = false) Integer performedById) {
        return maintenanceLogService.createLog(log, equipmentId, performedById);
    }

    @PutMapping("/{id}")
    public ApiResponse<MaintenanceLog> updateLog(@PathVariable Integer id, @RequestBody MaintenanceLog log) {
        return maintenanceLogService.updateLog(id, log);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteLog(@PathVariable Integer id) {
        return maintenanceLogService.deleteLog(id);
    }
}
