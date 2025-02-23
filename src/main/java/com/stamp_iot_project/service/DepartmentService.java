package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Department;
import com.stamp_iot_project.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public ApiResponse<List<Department>> getAllDepartments() {
        List<Department> list = departmentRepository.findAll();
        return new ApiResponse<>("success", "Fetched departments thành công", list, 200, new Date(), null);
    }

    public ApiResponse<Department> getDepartmentById(Integer id) {
        Optional<Department> opt = departmentRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Fetched department thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Department không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Department> createDepartment(Department dept) {
        Department saved = departmentRepository.save(dept);
        return new ApiResponse<>("success", "Department created", saved, 201, new Date(), null);
    }

    public ApiResponse<Department> updateDepartment(Integer id, Department dept) {
        Optional<Department> opt = departmentRepository.findById(id);
        if (opt.isPresent()) {
            Department existing = opt.get();
            existing.setName(dept.getName());
            existing.setDescription(dept.getDescription());
            Department updated = departmentRepository.save(existing);
            return new ApiResponse<>("success", "Department updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Department không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
        return new ApiResponse<>("success", "Department bị xoá", null, 200, new Date(), null);
    }
}

