package com.donor.auth.donor_auth_service.repository;

import com.donor.auth.donor_auth_service.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DonorRepository extends JpaRepository<Donor, Integer> {
    Optional<Donor> findByUserEmail(String userEmail);

    // Keep this for username-based searches if needed elsewhere
    Optional<Donor> findByUserName(String userName);
}