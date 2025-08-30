// CampaignDTO.java
package com.idealize.campaignservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.idealize.campaignservice.entity.Campaign;
import java.sql.Timestamp;
import java.time.LocalDate;

public class CampaignDTO {
    private Long id;
    private String title;
    private String description;
    private Campaign.CampaignType type;
    private String bloodType;
    private String supplyType;
    private Integer quantity;
    private double raised;
    private double goal;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Timestamp createdAt;
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Campaign.CampaignType getType() { return type; }
    public void setType(Campaign.CampaignType type) { this.type = type; }
    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }
    public String getSupplyType() { return supplyType; }
    public void setSupplyType(String supplyType) { this.supplyType = supplyType; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public double getRaised() { return raised; }
    public void setRaised(double raised) { this.raised = raised; }
    public double getGoal() { return goal; }
    public void setGoal(double goal) { this.goal = goal; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public CampaignDTO(Campaign campaign) {
        this.id = campaign.getId();
        this.title = campaign.getTitle();
        this.description = campaign.getDescription();
        this.type = campaign.getType();
        this.raised = campaign.getRaised();
        this.goal = campaign.getGoal();
        this.createdAt = campaign.getCreatedAt();
        this.endDate = campaign.getEndDate();

        if (campaign.getBloodCampaign() != null) {
            this.bloodType = campaign.getBloodCampaign().getBloodType().getDbValue();
            this.quantity = campaign.getBloodCampaign().getQuantity();
    } else if (campaign.getSupplyCampaign() != null) {
            this.supplyType = campaign.getSupplyCampaign().getSupplyType();
            this.quantity = campaign.getSupplyCampaign().getQuantity();
        }

    }
}