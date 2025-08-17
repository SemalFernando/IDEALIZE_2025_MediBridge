package com.ngo.auth.repository;

import com.ngo.auth.entity.Ngo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface NgoRepository extends JpaRepository<Ngo, Integer> {
    Optional<Ngo> findByEmail(String email);  // Changed parameter name
    Optional<Ngo> findByNgoName(String ngoName);  // Changed method name to match property
}
