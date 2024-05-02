package com.quanlyphongkhamvadatlich.medical_service;

import com.quanlyphongkhamvadatlich.entity.MedicalService;
import com.quanlyphongkhamvadatlich.medical_service.dto.CreateMedicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalServiceBusiness {
    private final MedicalServiceMapper medicalServiceMapper;
    private final MedicalServiceRepository medicalServiceRepository;

    public void createMedicalService(CreateMedicalService serviceParam){
        MedicalService newService = medicalServiceMapper.toEntity(serviceParam);

        medicalServiceRepository.save(newService);
    }
}
