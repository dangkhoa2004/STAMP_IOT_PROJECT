package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.Role;
import com.stamp_iot_project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ApiResponse<List<Role>> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ApiResponse<Role> getRoleById(@PathVariable Integer id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public ApiResponse<Role> createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PutMapping("/{id}")
    public ApiResponse<Role> updateRole(@PathVariable Integer id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteRole(@PathVariable Integer id) {
        return roleService.deleteRole(id);
    }
}
