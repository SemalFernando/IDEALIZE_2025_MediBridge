
package com.example.home.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "urgent_needs")
public class UrgentNeed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;
}
