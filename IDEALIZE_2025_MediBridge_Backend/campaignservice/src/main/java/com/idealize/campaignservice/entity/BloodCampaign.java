// BloodCampaign.java
package com.idealize.campaignservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "blood_campaigns")
public class BloodCampaign {

    @Id
    @Column(name = "campaign_id")
    private Long campaignId;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    private int quantity;

    @OneToOne
    @MapsId
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    // Getters and Setters
    public Long getCampaignId() { return campaignId; }
    public void setCampaignId(Long campaignId) { this.campaignId = campaignId; }
    public BloodType getBloodType() { return bloodType; }
    public void setBloodType(BloodType bloodType) { this.bloodType = bloodType; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Campaign getCampaign() { return campaign; }
    public void setCampaign(Campaign campaign) { this.campaign = campaign; }

    public enum BloodType {
        A_POS("A+"),
        A_NEG("A-"),
        B_POS("B+"),
        B_NEG("B-"),
        AB_POS("AB+"),
        AB_NEG("AB-"),
        O_POS("O+"),
        O_NEG("O-");

        private final String dbValue;

        BloodType(String dbValue) {
            this.dbValue = dbValue;
        }

        public String getDbValue() {
            return dbValue;
        }


        public static BloodType fromString(String value) {
            return switch (value) {
                case "A+" -> A_POS;
                case "A-" -> A_NEG;
                case "B+" -> B_POS;
                case "B-" -> B_NEG;
                case "AB+" -> AB_POS;
                case "AB-" -> AB_NEG;
                case "O+" -> O_POS;
                case "O-" -> O_NEG;
                default -> throw new IllegalArgumentException("Invalid blood type: " + value);
            };
        }
    }
}