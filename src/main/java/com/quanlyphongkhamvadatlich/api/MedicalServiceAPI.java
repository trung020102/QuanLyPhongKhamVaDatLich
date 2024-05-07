package com.quanlyphongkhamvadatlich.api;

import com.quanlyphongkhamvadatlich.dto.dashboard.MedicalServiceParam;
import com.quanlyphongkhamvadatlich.service.dashboard.MedicalServiceBusiness;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/medical-service")
@RequiredArgsConstructor
public class MedicalServiceAPI {
    private MedicalServiceBusiness medicalServiceBusiness;

    @PostMapping()
    public ResponseEntity<?> createService(@Valid @RequestBody MedicalServiceParam serviceParam) {
        medicalServiceBusiness.createMedicalService(serviceParam);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllServices() {
        return ResponseEntity.ok(medicalServiceBusiness.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(@PathVariable Long id, @Valid @RequestBody MedicalServiceParam serviceParam) {
        medicalServiceBusiness.updateMedicalService(id, serviceParam);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Long id) {
        medicalServiceBusiness.deleteMedicalService(id);
        return ResponseEntity.ok().build();
    }
}
