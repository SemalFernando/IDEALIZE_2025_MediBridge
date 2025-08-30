// CampaignController.java
package com.idealize.campaignservice.controller;

import com.idealize.campaignservice.dto.CampaignDTO;
import com.idealize.campaignservice.entity.Campaign;
import com.idealize.campaignservice.service.CampaignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.idealize.campaignservice.repository.CampaignRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/campaigns")
@CrossOrigin(origins = "http://localhost:3000")
public class CampaignController {

    private final CampaignService campaignService;
    private final CampaignRepository campaignRepository;

    public CampaignController(CampaignService campaignService, CampaignRepository campaignRepository) {
        this.campaignService = campaignService;
        this.campaignRepository = campaignRepository;
    }

    @PostMapping
    public ResponseEntity<?> createCampaign(@RequestBody Map<String, Object> campaignData) {
        try {
            CampaignDTO created = campaignService.createCampaign(campaignData); // ✅ DTO already
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<CampaignDTO>> getAllCampaigns() {
        List<Campaign> campaigns = campaignRepository.findAll();
        List<CampaignDTO> dtos = campaigns.stream()
                .map(CampaignDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }



    @GetMapping("/notifications")
    public ResponseEntity<List<Map<String, Object>>> getCampaignNotifications() {
        List<Campaign> campaigns = campaignRepository.findTop5ByOrderByCreatedAtDesc();
        List<Map<String, Object>> notifications = new ArrayList<>();

        for (Campaign campaign : campaigns) {
            Map<String, Object> notification = new HashMap<>();
            notification.put("id", campaign.getId());
            notification.put("title", campaign.getTitle());
            notification.put("description", "New campaign added: " + campaign.getTitle());
            notification.put("createdAt", campaign.getCreatedAt());
            notification.put("type", campaign.getType().name());
            notifications.add(notification);
        }

        return ResponseEntity.ok(notifications);
    }
}