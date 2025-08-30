// CampaignRepository.java
package com.idealize.campaignservice.repository;

import com.idealize.campaignservice.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findTop5ByOrderByCreatedAtDesc();

}