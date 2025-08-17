package com.example.home.controller;

import com.example.home.model.Hospital;
import com.example.home.repository.HospitalRepository;
import com.example.home.service.ImageUploadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@CrossOrigin(origins = "*")
public class HospitalController {

    private final HospitalRepository hospitalRepository;
    private final ImageUploadService imageUploadService;

    public HospitalController(HospitalRepository hospitalRepository, ImageUploadService imageUploadService) {
        this.hospitalRepository = hospitalRepository;
        this.imageUploadService = imageUploadService;
    }

    // Get all hospitals
    @GetMapping
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    // Add hospital with image upload
    @PostMapping
    public Hospital addHospital(
            @RequestParam("name") String name,
            @RequestParam("location") String location,
            @RequestParam("contactNumber") String contactNumber,
            @RequestParam("image") MultipartFile imageFile
    ) throws IOException {

        // Upload image and get URL
        String imageUrl = imageUploadService.uploadImage(imageFile);

        // Save hospital data
        Hospital hospital = new Hospital();
        hospital.setName(name);
        hospital.setLocation(location);
        hospital.setContactNumber(contactNumber);
        hospital.setImageUrl(imageUrl);

        return hospitalRepository.save(hospital);
    }
}
