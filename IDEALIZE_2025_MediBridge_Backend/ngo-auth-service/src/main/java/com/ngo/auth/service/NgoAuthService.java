package com.ngo.auth.service;

import com.ngo.auth.dto.NgoRegistrationRequest;
import com.ngo.auth.entity.Ngo;
import com.ngo.auth.repository.NgoRepository;
import com.ngo.auth.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class NgoAuthService {
    private final NgoRepository ngoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final String uploadDir = "uploads/";

    public NgoAuthService(NgoRepository ngoRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.ngoRepository = ngoRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;

        // Create upload directory if it doesn't exist
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory", e);
        }
    }

    public String registerNgo(NgoRegistrationRequest request, MultipartFile registrationProof) {
        if (ngoRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        Ngo ngo = new Ngo();
        ngo.setNgoName(request.getNgoName());
        ngo.setRegistrationNo(request.getRegistrationNo());
        ngo.setTypeOfNgo(request.getTypeOfNgo());
        ngo.setEmail(request.getEmail());
        ngo.setPhone(request.getPhone());
        ngo.setAddress(request.getAddress());
        ngo.setAdminName(request.getAdminName());
        ngo.setPassword(passwordEncoder.encode(request.getPassword()));

        if (registrationProof != null && !registrationProof.isEmpty()) {
            try {
                String fileName = UUID.randomUUID() + "_" + registrationProof.getOriginalFilename();
                Path filePath = Paths.get(uploadDir + fileName);
                Files.copy(registrationProof.getInputStream(), filePath);
                ngo.setRegistrationProofImage(fileName);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store registration proof", e);
            }
        }

        ngoRepository.save(ngo);
        return "NGO registered successfully";
    }


    public Map<String, Object> loginNgo(String email, String password) {
        Ngo ngo = ngoRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("NGO not found with this email"));

        if (passwordEncoder.matches(password, ngo.getPassword())) {
            String token = jwtUtil.generateToken(ngo.getNgoName(), "ngo", ngo.getId());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("ngo", ngo);
            return response;
        }
        throw new RuntimeException("Invalid password");
    }

    public String updateNgo(Integer id, NgoRegistrationRequest request) {
        Ngo existingNgo = ngoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NGO not found with ID: " + id));

        if (!existingNgo.getEmail().equals(request.getEmail()) &&
                ngoRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        existingNgo.setNgoName(request.getNgoName());
        existingNgo.setRegistrationNo(request.getRegistrationNo());
        existingNgo.setTypeOfNgo(request.getTypeOfNgo());
        existingNgo.setEmail(request.getEmail());
        existingNgo.setPhone(request.getPhone());
        existingNgo.setAddress(request.getAddress());
        existingNgo.setAdminName(request.getAdminName());
        existingNgo.setPassword(passwordEncoder.encode(request.getPassword()));
        existingNgo.setRegistrationProofImage(request.getRegistrationProofImage());

        ngoRepository.save(existingNgo);
        return "NGO updated successfully";
    }


    public void deleteNgo(Integer id) {
        Ngo ngo = ngoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NGO not found with ID: " + id));
        ngoRepository.delete(ngo);
    }

    public Ngo findNgoById(Integer id) {
        return ngoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NGO not found with ID: " + id));
    }

    public Ngo findNgoByName(String name) {
        return ngoRepository.findByNgoName(name)
                .orElseThrow(() -> new RuntimeException("NGO not found with name: " + name));
    }
}