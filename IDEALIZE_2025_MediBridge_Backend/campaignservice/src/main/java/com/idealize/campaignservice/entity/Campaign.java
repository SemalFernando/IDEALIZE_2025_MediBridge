    // Campaign.java
    package com.idealize.campaignservice.entity;

    import jakarta.persistence.*;
    import java.time.LocalDate;

    @Entity
    @Table(name = "campaigns")
    public class Campaign {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;
        private String description;

        @Enumerated(EnumType.STRING)
        private CampaignType type;

        @Column(name = "end_date")
        private LocalDate endDate;

        private double raised = 0;
        private double goal = 0;

        @Column(name = "created_at", updatable = false, insertable = false)
        private java.sql.Timestamp createdAt;

        @OneToOne(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
        private BloodCampaign bloodCampaign;

        @OneToOne(mappedBy = "campaign", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
        private SupplyCampaign supplyCampaign;

        @Column(name = "status")
        @Convert(converter = CampaignStatusConverter.class)
        private CampaignStatus status = CampaignStatus.ACTIVE;

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public CampaignType getType() { return type; }
        public void setType(CampaignType type) { this.type = type; }
        public LocalDate getEndDate() { return endDate; }
        public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
        public CampaignStatus getStatus() { return status; }
        public void setStatus(CampaignStatus status) { this.status = status; }
        public double getRaised() { return raised; }
        public void setRaised(double raised) { this.raised = raised; }
        public double getGoal() { return goal; }
        public void setGoal(double goal) { this.goal = goal; }
        public java.sql.Timestamp getCreatedAt() { return createdAt; }
        public void setCreatedAt(java.sql.Timestamp createdAt) { this.createdAt = createdAt; }
        public BloodCampaign getBloodCampaign() { return bloodCampaign; }
        public void setBloodCampaign(BloodCampaign bloodCampaign) { this.bloodCampaign = bloodCampaign; }
        public SupplyCampaign getSupplyCampaign() { return supplyCampaign; }
        public void setSupplyCampaign(SupplyCampaign supplyCampaign) { this.supplyCampaign = supplyCampaign; }

        public enum CampaignType {
            BLOOD, SUPPLIES
        }

        public enum CampaignStatus {
            ACTIVE("active"),
            INACTIVE("inactive"),
            COMPLETED("completed");

            private final String dbValue;

            CampaignStatus(String dbValue) {
                this.dbValue = dbValue;
            }

            public String getDbValue() {
                return dbValue;
            }

            public static CampaignStatus fromDbValue(String dbValue) {
                for (CampaignStatus status : CampaignStatus.values()) {
                    if (status.dbValue.equals(dbValue)) {
                        return status;
                    }
                }
                throw new IllegalArgumentException("Unknown status: " + dbValue);
            }
        }
    }