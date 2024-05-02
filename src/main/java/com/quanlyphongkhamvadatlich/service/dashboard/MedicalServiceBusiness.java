package com.quanlyphongkhamvadatlich.service.dashboard;

import com.quanlyphongkhamvadatlich.dto.dashboard.MedicalService;
import com.quanlyphongkhamvadatlich.mapper.MedicalServiceMapper;
import com.quanlyphongkhamvadatlich.repository.MedicalServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalServiceBusiness {
    private final MedicalServiceMapper medicalServiceMapper;
    private final MedicalServiceRepository medicalServiceRepository;

    public void createMedicalService(MedicalService serviceParam){
        com.quanlyphongkhamvadatlich.entity.MedicalService newService = medicalServiceMapper.toEntity(serviceParam);

        medicalServiceRepository.save(newService);
    }
}
