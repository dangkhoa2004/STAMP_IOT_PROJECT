package com.stamp_iot_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.entity.User;
import com.stamp_iot_project.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiResponse<User> login(@RequestParam String username, @RequestParam String passwordHash) {
        return userService.loginByUsername(username, passwordHash);
    }
}
