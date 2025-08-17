// UrgentNeedController.java
package com.example.home.controller;

import com.example.home.model.UrgentNeed;
import com.example.home.repository.UrgentNeedRepository;
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

    @GetMapping
    public List<UrgentNeed> getAllUrgentNeeds() {
        return urgentNeedRepository.findAll();
    }

    @PostMapping
    public UrgentNeed addUrgentNeed(@RequestBody UrgentNeed urgentNeed) {
        return urgentNeedRepository.save(urgentNeed);
    }
}
