package com.ngo.auth.controller;

import com.ngo.auth.dto.NgoRegistrationRequest;
import com.ngo.auth.dto.NgoLoginRequest;
import com.ngo.auth.entity.Ngo;
import com.ngo.auth.service.NgoAuthService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/ngo")
public class NgoAuthController {

    private final NgoAuthService ngoAuthService;

    public NgoAuthController(NgoAuthService ngoAuthService) {
        this.ngoAuthService = ngoAuthService;
    }

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> registerNgo(
            @RequestPart @Valid NgoRegistrationRequest data,
            @RequestPart(required = false) MultipartFile registrationProof) {
        return ResponseEntity.ok(ngoAuthService.registerNgo(data, registrationProof));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginNgo(@Valid @RequestBody NgoLoginRequest request) {
        return ResponseEntity.ok(ngoAuthService.loginNgo(
                request.getEmail(),
                request.getPassword()
        ));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateNgo(@PathVariable Integer id,
                                            @Valid @RequestBody NgoRegistrationRequest request) {
        return ResponseEntity.ok(ngoAuthService.updateNgo(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNgo(@PathVariable Integer id) {
        ngoAuthService.deleteNgo(id);
        return ResponseEntity.ok("NGO deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ngo> getNgoById(@PathVariable Integer id) {
        return ResponseEntity.ok(ngoAuthService.findNgoById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Ngo> getNgoByName(@PathVariable String name) {
        return ResponseEntity.ok(ngoAuthService.findNgoByName(name));
    }
}
