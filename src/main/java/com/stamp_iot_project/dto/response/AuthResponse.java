package com.stamp_iot_project.dto.response;

import com.stamp_iot_project.entity.User;

public class AuthResponse {
    private String token;
    private User user;
    private long expirationTime; // Thêm trường thông tin về thời gian hết hạn của token
    private String tokenType; // Thêm trường thông tin về loại token (ví dụ: Bearer)

    public AuthResponse(String token, User user, long expirationTime, String tokenType) {
        this.token = token;
        this.user = user;
        this.expirationTime = expirationTime;
        this.tokenType = tokenType;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}