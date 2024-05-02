package com.quanlyphongkhamvadatlich.medical_service;

import com.quanlyphongkhamvadatlich.entity.MedicalService;
import com.quanlyphongkhamvadatlich.medical_service.dto.CreateMedicalService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MedicalServiceMapper {
    private ModelMapper modelMapper;

    public MedicalServiceMapper() {
        this.modelMapper = new ModelMapper();
    }

    public MedicalService toEntity(CreateMedicalService serviceParam) {
        return this.modelMapper.map(serviceParam, MedicalService.class);
    }
}
