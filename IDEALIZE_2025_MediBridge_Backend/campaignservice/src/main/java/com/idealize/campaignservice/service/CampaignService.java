// CampaignService.java (fixed)
package com.idealize.campaignservice.service;

import com.idealize.campaignservice.dto.CampaignDTO;
import com.idealize.campaignservice.entity.*;
import com.idealize.campaignservice.repository.CampaignRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Transactional
    public CampaignDTO createCampaign(Map<String, Object> payload) {
        // Validate required fields
        validatePayload(payload);

        String title = (String) payload.get("title");
        String description = (String) payload.get("description");
        String type = ((String) payload.get("type")).toUpperCase();
        String endDate = (String) payload.get("endDate");
        double goal = parseGoal(payload.get("goal"));
        Map<String, Object> typeData = (Map<String, Object>) payload.get("typeData");

        Campaign.CampaignType campaignType;
        try {
            campaignType = Campaign.CampaignType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid campaign type: " + type);
        }

        LocalDate parsedEndDate;
        try {
            parsedEndDate = LocalDate.parse(endDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected yyyy-MM-dd");
        }

        Campaign campaign = new Campaign();
        campaign.setTitle(title);
        campaign.setDescription(description);
        campaign.setEndDate(parsedEndDate);
        campaign.setType(campaignType);
        campaign.setGoal(goal); // Set the goal amount

        // Create type-specific campaign
        if (campaignType == Campaign.CampaignType.BLOOD) {
            createBloodCampaign(campaign, typeData);
        } else {
            createSupplyCampaign(campaign, typeData);
        }

        Campaign savedCampaign = campaignRepository.save(campaign);
        return new CampaignDTO(savedCampaign);
    }

    private void validatePayload(Map<String, Object> payload) {
        if (payload.get("title") == null) throw new IllegalArgumentException("Title is required");
        if (payload.get("description") == null) throw new IllegalArgumentException("Description is required");
        if (payload.get("type") == null) throw new IllegalArgumentException("Type is required");
        if (payload.get("endDate") == null) throw new IllegalArgumentException("End date is required");
        if (payload.get("goal") == null) throw new IllegalArgumentException("Goal amount is required");
        if (payload.get("typeData") == null) throw new IllegalArgumentException("Type data is required");
        if (!(payload.get("typeData") instanceof Map)) throw new IllegalArgumentException("Type data must be an object");
    }

    private double parseGoal(Object goalObj) {
        if (goalObj instanceof Number) {
            return ((Number) goalObj).doubleValue();
        } else if (goalObj instanceof String) {
            try {
                return Double.parseDouble((String) goalObj);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Goal must be a valid number");
            }
        }
        throw new IllegalArgumentException("Goal must be a number");
    }

    private void createBloodCampaign(Campaign campaign, Map<String, Object> typeData) {
        if (typeData.get("bloodType") == null) throw new IllegalArgumentException("Blood type is required");
        if (typeData.get("quantity") == null) throw new IllegalArgumentException("Quantity is required");

        String bloodTypeStr = (String) typeData.get("bloodType");
        int quantity = parseQuantity(typeData.get("quantity"));

        BloodCampaign bloodCampaign = new BloodCampaign();
        try {
            bloodCampaign.setBloodType(BloodCampaign.BloodType.fromString(bloodTypeStr));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid blood type: " + bloodTypeStr);
        }
        bloodCampaign.setQuantity(quantity);
        bloodCampaign.setCampaign(campaign);
        campaign.setBloodCampaign(bloodCampaign);
    }

    private void createSupplyCampaign(Campaign campaign, Map<String, Object> typeData) {
        if (typeData.get("supplyType") == null) throw new IllegalArgumentException("Supply type is required");
        if (typeData.get("quantity") == null) throw new IllegalArgumentException("Quantity is required");

        String supplyType = (String) typeData.get("supplyType");
        int quantity = parseQuantity(typeData.get("quantity"));

        SupplyCampaign supplyCampaign = new SupplyCampaign();
        supplyCampaign.setSupplyType(supplyType);
        supplyCampaign.setQuantity(quantity);
        supplyCampaign.setCampaign(campaign);
        campaign.setSupplyCampaign(supplyCampaign);
    }

    private int parseQuantity(Object quantityObj) {
        if (quantityObj instanceof Number) {
            return ((Number) quantityObj).intValue();
        } else if (quantityObj instanceof String) {
            try {
                return Integer.parseInt((String) quantityObj);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Quantity must be a valid number");
            }
        }
        throw new IllegalArgumentException("Quantity must be a number");
    }
}