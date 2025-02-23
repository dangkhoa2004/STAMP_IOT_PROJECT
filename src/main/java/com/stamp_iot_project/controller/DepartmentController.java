package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Department;
import com.stamp_iot_project.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ApiResponse<List<Department>> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public ApiResponse<Department> getDepartmentById(@PathVariable Integer id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public ApiResponse<Department> createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @PutMapping("/{id}")
    public ApiResponse<Department> updateDepartment(@PathVariable Integer id, @RequestBody Department department) {
        return departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteDepartment(@PathVariable Integer id) {
        return departmentService.deleteDepartment(id);
    }
}

