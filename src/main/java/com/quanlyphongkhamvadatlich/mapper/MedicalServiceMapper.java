package com.quanlyphongkhamvadatlich.mapper;

import com.quanlyphongkhamvadatlich.dto.dashboard.MedicalService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MedicalServiceMapper {
    private ModelMapper modelMapper;

    public MedicalServiceMapper() {
        this.modelMapper = new ModelMapper();
    }

    public com.quanlyphongkhamvadatlich.entity.MedicalService toEntity(MedicalService serviceParam) {
        return this.modelMapper.map(serviceParam, com.quanlyphongkhamvadatlich.entity.MedicalService.class);
    }
}
