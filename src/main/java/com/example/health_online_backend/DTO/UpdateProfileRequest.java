package com.example.health_online_backend.DTO;

public class UpdateProfileRequest {
    private String id;
    private String fullName;
    private String phoneNumber;


    public UpdateProfileRequest() {
    }

    public UpdateProfileRequest(String id, String fullName, String phoneNumber) {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
