// SupplyCampaign.java
package com.idealize.campaignservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "supply_campaigns")
public class SupplyCampaign {

    @Id
    @Column(name = "campaign_id")
    private Long campaignId;

    @Column(name = "supply_type")
    private String supplyType;

    private int quantity;

    @OneToOne
    @MapsId
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    // Getters and Setters
    public Long getCampaignId() { return campaignId; }
    public void setCampaignId(Long campaignId) { this.campaignId = campaignId; }
    public String getSupplyType() { return supplyType; }
    public void setSupplyType(String supplyType) { this.supplyType = supplyType; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Campaign getCampaign() { return campaign; }
    public void setCampaign(Campaign campaign) { this.campaign = campaign; }
}