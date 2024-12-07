package com.example.health_online_backend.DTO;

import com.example.health_online_backend.models.User;

public class AuthResponse {
    private boolean success;
    private String message;
    private User user;  // Chỉ trả về khi login thành công

    public AuthResponse(boolean success, String message, User user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    // Nếu login thất bại, chỉ cần success và message
    public AuthResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.user = null;
    }

    // Getters và Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
