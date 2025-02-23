package com.stamp_iot_project.service;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Department;
import com.stamp_iot_project.entity.Employee;
import com.stamp_iot_project.repository.DepartmentRepository;
import com.stamp_iot_project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public ApiResponse<List<Employee>> getAllEmployees() {
        List<Employee> list = employeeRepository.findAll();
        return new ApiResponse<>("success", "Fetched employees thành công", list, 200, new Date(), null);
    }

    public ApiResponse<Employee> getEmployeeById(Integer id) {
        Optional<Employee> opt = employeeRepository.findById(id);
        if (opt.isPresent()) {
            return new ApiResponse<>("success", "Fetched employee thành công", opt.get(), 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Employee không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Employee> createEmployee(Employee emp, Integer departmentId) {
        if (departmentId != null) {
            Optional<Department> deptOpt = departmentRepository.findById(departmentId);
            deptOpt.ifPresent(emp::setDepartment);
        }
        Employee saved = employeeRepository.save(emp);
        return new ApiResponse<>("success", "Employee created", saved, 201, new Date(), null);
    }

    public ApiResponse<Employee> updateEmployee(Integer id, Employee emp) {
        Optional<Employee> opt = employeeRepository.findById(id);
        if (opt.isPresent()) {
            Employee existing = opt.get();
            existing.setName(emp.getName());
            existing.setContactInfo(emp.getContactInfo());
            existing.setHireDate(emp.getHireDate());
            existing.setSalary(emp.getSalary());
            // Nếu có department update
            if (emp.getDepartment() != null && emp.getDepartment().getDepartmentId() != null) {
                Optional<Department> deptOpt = departmentRepository.findById(emp.getDepartment().getDepartmentId());
                deptOpt.ifPresent(existing::setDepartment);
            }
            Employee updated = employeeRepository.save(existing);
            return new ApiResponse<>("success", "Employee updated", updated, 200, new Date(), null);
        }
        return new ApiResponse<>("error", "Employee không tìm thấy", null, 404, new Date(), null);
    }

    public ApiResponse<Void> deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
        return new ApiResponse<>("success", "Employee deleted", null, 200, new Date(), null);
    }
}

