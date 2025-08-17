package com.example.home.controller;

import com.example.home.model.UrgentNeed;
import com.example.home.repository.UrgentNeedRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/urgent-needs")
@CrossOrigin(origins = "*")
public class UrgentNeedController {

    private final UrgentNeedRepository urgentNeedRepository;

    public UrgentNeedController(UrgentNeedRepository urgentNeedRepository) {
        this.urgentNeedRepository = urgentNeedRepository;
    }

    @GetMapping("/count")
    public long getUrgentNeedCount() {
        return urgentNeedRepository.count();
    }

    @GetMapping
    public List<UrgentNeed> getAllUrgentNeeds() {
        return urgentNeedRepository.findAllByOrderByCreatedAtDesc();
    }

    @PostMapping
    public ResponseEntity<UrgentNeed> createUrgentNeed(@RequestBody UrgentNeedRequest request) {
        UrgentNeed urgentNeed = new UrgentNeed();
        urgentNeed.setTitle(request.getTitle());
        urgentNeed.setDescription(request.getDescription());

        UrgentNeed savedNeed = urgentNeedRepository.save(urgentNeed);
        return ResponseEntity.ok(savedNeed);
    }

    // Request DTO
    public static class UrgentNeedRequest {
        private String title;
        private String description;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}