package com.example.home.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "donor_reviews")
public class DonorReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String donorName;

    @Column(length = 2000)
    private String reviewText;

    private String donorImage; // URL to donor's image
}
