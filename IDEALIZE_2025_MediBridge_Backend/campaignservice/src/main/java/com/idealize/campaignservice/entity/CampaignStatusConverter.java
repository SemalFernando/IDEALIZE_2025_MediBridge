package com.idealize.campaignservice.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CampaignStatusConverter implements AttributeConverter<Campaign.CampaignStatus, String> {

    @Override
    public String convertToDatabaseColumn(Campaign.CampaignStatus attribute) {
        return attribute != null ? attribute.getDbValue() : null;
    }

    @Override
    public Campaign.CampaignStatus convertToEntityAttribute(String dbData) {
        return dbData != null ? Campaign.CampaignStatus.fromDbValue(dbData) : null;
    }
}