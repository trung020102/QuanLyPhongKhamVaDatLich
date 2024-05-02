package com.quanlyphongkhamvadatlich.api;

import com.quanlyphongkhamvadatlich.dto.dashboard.MedicalService;
import com.quanlyphongkhamvadatlich.service.dashboard.MedicalServiceBusiness;
import com.quanlyphongkhamvadatlich.repository.MedicalServiceRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/medical-service")
@RequiredArgsConstructor
public class MedicalServiceAPI {
    private final MedicalServiceBusiness medicalServiceBusiness;
    private final MedicalServiceRepository medicalServiceRepository;
    @PostMapping
    public ResponseEntity<?> createService(@Valid @RequestBody MedicalService serviceParam){
        medicalServiceBusiness.createMedicalService(serviceParam);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllServices(){
        return ResponseEntity.ok(medicalServiceRepository.findAll());
    }
}
