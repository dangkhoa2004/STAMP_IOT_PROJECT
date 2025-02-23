package com.stamp_iot_project.controller;

import com.stamp_iot_project.configuration.JwtUtil;
import com.stamp_iot_project.dto.response.ApiResponse;
import com.stamp_iot_project.dto.response.AuthResponse;
import com.stamp_iot_project.entity.User;
import com.stamp_iot_project.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestParam String username, @RequestParam String password) {
        ApiResponse<User> response = userService.findByUsername(username);

        if (response.getStatus().equals("success")) {
            User user = response.getData();
            if (user != null && user.getPassword().equals(password)) {
                String token = jwtUtil.generateToken(user);
                Claims claims = jwtUtil.extractAllClaims(token);
                long expirationTime = claims.getExpiration().getTime();
                String tokenType = "Bearer";
                AuthResponse authResponse = new AuthResponse(token, user, expirationTime, tokenType);
                return ResponseEntity.ok(authResponse);
            } else {
                throw new UsernameNotFoundException("Sai mật khẩu!");
            }
        } else {
            throw new UsernameNotFoundException("Người dùng không tồn tại!");
        }
    }
}
