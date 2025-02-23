package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Employee;
import com.stamp_iot_project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ApiResponse<List<Employee>> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ApiResponse<Employee> getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    // Giả sử để tạo Employee, nếu có departmentId truyền kèm
    @PostMapping("/{departmentId}")
    public ApiResponse<Employee> createEmployee(@RequestBody Employee employee,
                                                @PathVariable(required = false) Integer departmentId) {
        return employeeService.createEmployee(employee, departmentId);
    }

    @PutMapping("/{id}")
    public ApiResponse<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteEmployee(@PathVariable Integer id) {
        return employeeService.deleteEmployee(id);
    }
}
