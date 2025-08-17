package com.ngo.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class NgoRegistrationRequest {

    @NotBlank private String ngoName;
    @NotBlank private String registrationNo;
    @NotBlank private String typeOfNgo;
    @NotBlank @Email private String email;
    @NotBlank private String phone;
    @NotBlank private String address;
    @NotBlank private String adminName;
    @NotBlank private String password;
    private String registrationProofImage;

    // Getters and Setters

    public String getNgoName() { return ngoName; }
    public void setNgoName(String ngoName) { this.ngoName = ngoName; }

    public String getRegistrationNo() { return registrationNo; }
    public void setRegistrationNo(String registrationNo) { this.registrationNo = registrationNo; }

    public String getTypeOfNgo() { return typeOfNgo; }
    public void setTypeOfNgo(String typeOfNgo) { this.typeOfNgo = typeOfNgo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getAdminName() { return adminName; }
    public void setAdminName(String adminName) { this.adminName = adminName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRegistrationProofImage() { return registrationProofImage; }
    public void setRegistrationProofImage(String registrationProofImage) { this.registrationProofImage = registrationProofImage; }
}
