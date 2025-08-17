package com.example.home.repository;

import com.example.home.model.UrgentNeed;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UrgentNeedRepository extends JpaRepository<UrgentNeed, Long> {
    List<UrgentNeed> findAllByOrderByCreatedAtDesc();
}