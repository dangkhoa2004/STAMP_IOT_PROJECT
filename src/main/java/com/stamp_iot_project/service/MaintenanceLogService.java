package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Equipment;
import com.stamp_iot_project.entity.MaintenanceLog;
import com.stamp_iot_project.repository.EmployeeRepository;
import com.stamp_iot_project.repository.EquipmentRepository;
import com.stamp_iot_project.repository.MaintenanceLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceLogService {

    @Autowired
    private MaintenanceLogRepository maintenanceLogRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public ApiResponse<List<MaintenanceLog>> getAllLogs() {
        List<MaintenanceLog> list = maintenanceLogRepository.findAll();
        return new ApiResponse<>("success", "Đã tìm nạp maintenance logs thành công", list, 200, new Date(), null);
    }

    public ApiResponse<MaintenanceLog> getLogById(Integer id) {
        Optional<MaintenanceLog> opt = maintenanceLogRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Đã tìm nạp maintenance log thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Maintenance log không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<MaintenanceLog> createLog(MaintenanceLog log, Integer equipmentId, Integer performedById) {
        Optional<Equipment> eqOpt = equipmentRepository.findById(equipmentId);
        eqOpt.ifPresent(log::setEquipment);
        if (performedById != null) {
            employeeRepository.findById(performedById).ifPresent(log::setPerformedBy);
        }
        MaintenanceLog saved = maintenanceLogRepository.save(log);
        return new ApiResponse<>("success", "Maintenance log created", saved, 201, new Date(), null);
    }

    public ApiResponse<MaintenanceLog> updateLog(Integer id, MaintenanceLog log) {
        Optional<MaintenanceLog> opt = maintenanceLogRepository.findById(id);
        if (opt.isPresent()) {
            MaintenanceLog existing = opt.get();
            existing.setMaintenanceDate(log.getMaintenanceDate());
            existing.setMaintenanceType(log.getMaintenanceType());
            existing.setMaintenanceDescription(log.getMaintenanceDescription());
            existing.setCost(log.getCost());
            existing.setNextScheduledDate(log.getNextScheduledDate());
            existing.setStatus(log.getStatus());
            MaintenanceLog updated = maintenanceLogRepository.save(existing);
            return new ApiResponse<>("success", "Maintenance log updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Maintenance log không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteLog(Integer id) {
        maintenanceLogRepository.deleteById(id);
        return new ApiResponse<>("success", "Maintenance log deleted", null, 200, new Date(), null);
    }
}

