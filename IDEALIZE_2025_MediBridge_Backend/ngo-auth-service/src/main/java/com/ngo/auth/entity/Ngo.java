package com.ngo.auth.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ngos")
public class Ngo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ngo_name", nullable = false)
    private String ngoName;

    @Column(name = "registration_no", nullable = false)
    private String registrationNo;

    @Column(name = "type_of_ngo", nullable = false)
    private String typeOfNgo;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "admin_name", nullable = false)
    private String adminName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "registration_proof_image")
    private String registrationProofImage;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    // Getters and Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

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

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
