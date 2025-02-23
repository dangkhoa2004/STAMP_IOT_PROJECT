package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Department;
import com.stamp_iot_project.entity.Equipment;
import com.stamp_iot_project.repository.DepartmentRepository;
import com.stamp_iot_project.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public ApiResponse<List<Equipment>> getAllEquipment() {
        List<Equipment> list = equipmentRepository.findAll();
        return new ApiResponse<>("success", "Đã tìm nạp equipment thành công", list, 200, new Date(), null);
    }

    public ApiResponse<Equipment> getEquipmentById(Integer id) {
        Optional<Equipment> opt = equipmentRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Đã tìm nạp equipment thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Equipment không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Equipment> createEquipment(Equipment eq, Integer departmentId) {
        if (departmentId != null) {
            Optional<Department> deptOpt = departmentRepository.findById(departmentId);
            deptOpt.ifPresent(eq::setDepartment);
        }
        Equipment saved = equipmentRepository.save(eq);
        return new ApiResponse<>("success", "Equipment created", saved, 201, new Date(), null);
    }

    public ApiResponse<Equipment> updateEquipment(Integer id, Equipment eq) {
        Optional<Equipment> opt = equipmentRepository.findById(id);
        if (opt.isPresent()) {
            Equipment existing = opt.get();
            existing.setName(eq.getName());
            existing.setType(eq.getType());
            existing.setPurchaseDate(eq.getPurchaseDate());
            existing.setMaintenanceSchedule(eq.getMaintenanceSchedule());
            existing.setStatus(eq.getStatus());
            // Update department nếu cần
            if (eq.getDepartment() != null && eq.getDepartment().getDepartmentId() != null) {
                Optional<Department> deptOpt = departmentRepository.findById(eq.getDepartment().getDepartmentId());
                deptOpt.ifPresent(existing::setDepartment);
            }
            Equipment updated = equipmentRepository.save(existing);
            return new ApiResponse<>("success", "Equipment updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Equipment không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteEquipment(Integer id) {
        equipmentRepository.deleteById(id);
        return new ApiResponse<>("success", "Equipment deleted", null, 200, new Date(), null);
    }
}

