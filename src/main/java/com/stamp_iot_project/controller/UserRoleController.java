package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.UserRole;
import com.stamp_iot_project.service.UserRoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    public ApiResponse<List<UserRole>> getAllUserRole() {
        return userRoleService.getAllUserRole();
    }

    // Tạo UserRole từ userId và roleId
    @PostMapping("/{userId}/{roleId}")
    public ApiResponse<UserRole> createUserRole(@PathVariable Integer userId,
            @PathVariable Integer roleId) {
        return userRoleService.createUserRole(userId, roleId);
    }

    // Xóa UserRole theo userId và roleId
    @DeleteMapping("/{userId}/{roleId}")
    public ApiResponse<Void> deleteUserRole(@PathVariable Integer userId,
            @PathVariable Integer roleId) {
        return userRoleService.deleteUserRole(userId, roleId);
    }
}
