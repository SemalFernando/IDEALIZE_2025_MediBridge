package com.example.home.controller;

import com.example.home.model.DonorReview;
import com.example.home.repository.DonorReviewRepository;
import com.example.home.service.ImageUploadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/donor-reviews")
@CrossOrigin(origins = "*")
public class DonorReviewController {

    private final DonorReviewRepository donorReviewRepository;
    private final ImageUploadService imageUploadService;

    public DonorReviewController(DonorReviewRepository donorReviewRepository, ImageUploadService imageUploadService) {
        this.donorReviewRepository = donorReviewRepository;
        this.imageUploadService = imageUploadService;
    }

    @GetMapping
    public List<DonorReview> getAllReviews() {
        return donorReviewRepository.findAll();
    }

    @PostMapping
    public DonorReview addReview(
            @RequestParam("donorName") String donorName,
            @RequestParam("reviewText") String reviewText,
            @RequestParam("donorImage") MultipartFile donorImageFile
    ) throws IOException {

        // Upload image to Cloudinary
        String imageUrl = imageUploadService.uploadImage(donorImageFile);

        // Save review
        DonorReview review = new DonorReview();
        review.setDonorName(donorName);
        review.setReviewText(reviewText);
        review.setDonorImage(imageUrl);

        return donorReviewRepository.save(review);
    }
}
