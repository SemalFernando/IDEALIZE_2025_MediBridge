package com.example.home.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hospitals")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String contactNumber;
    private String imageUrl; // Logo or photo of hospital
}
