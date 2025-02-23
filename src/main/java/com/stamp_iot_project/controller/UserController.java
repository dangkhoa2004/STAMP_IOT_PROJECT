package com.stamp_iot_project.controller;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.User;
import com.stamp_iot_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ApiResponse<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    // Khi tạo User, nếu có employeeId
    @PostMapping("/{employeeId}")
    public ApiResponse<User> createUser(@RequestBody User user,
                                        @PathVariable(required = false) Integer employeeId) {
        return userService.createUser(user, employeeId);
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}
