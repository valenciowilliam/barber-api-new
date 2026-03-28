package com.barber.barber_api.auth;

public class AuthResponse {

    private String token;
    private Long userId; // ✅ ADD THIS

    public AuthResponse(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public Long getUserId() {
        return userId;
    }
}