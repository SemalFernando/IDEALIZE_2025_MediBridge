package com.donor.auth.donor_auth_service.dto;

public class DonorLoginRequest {
    private String userEmail;  // Changed from userName
    private String userPassword;

    // Updated constructor
    public DonorLoginRequest(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    // Getters and Setters
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}