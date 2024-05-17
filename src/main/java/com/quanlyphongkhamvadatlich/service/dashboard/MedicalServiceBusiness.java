package com.quanlyphongkhamvadatlich.service.dashboard;

import com.quanlyphongkhamvadatlich.dto.dashboard.MedicalServiceParam;
import com.quanlyphongkhamvadatlich.dto.dashboard.MedicalServiceResult;
import com.quanlyphongkhamvadatlich.entity.MedicalService;
import com.quanlyphongkhamvadatlich.entity.Medicine;
import com.quanlyphongkhamvadatlich.mapper.MedicalServiceMapper;
import com.quanlyphongkhamvadatlich.repository.MedicalServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicalServiceBusiness {
    private final MedicalServiceMapper medicalServiceMapper;
    private final MedicalServiceRepository medicalServiceRepository;

    public void createMedicalService(MedicalServiceParam serviceParam) {
        MedicalService newService = medicalServiceMapper.toEntity(serviceParam);

        medicalServiceRepository.save(newService);
    }

    public List<MedicalServiceResult> getAll() {
        List<MedicalService> medicalServices = medicalServiceRepository.findAll();
        return medicalServices.stream().map(medicalServiceMapper::toDto).toList();
    }

    public void updateMedicalService(Long id, MedicalServiceParam serviceParam) {
        MedicalService medicalService = medicalServiceRepository.findById(id).orElseThrow();
        MedicalService newService = medicalServiceMapper.toEntity(serviceParam);
        newService.setId(medicalService.getId());

        medicalServiceRepository.save(newService);
    }

    public void deleteMedicalService(Long id) {
        MedicalService medicalService = medicalServiceRepository.findById(id).orElseThrow();
        medicalServiceRepository.delete(medicalService);
    }
    public List<MedicalService> getMedicalService(){
        return medicalServiceRepository.findAll();
    }
    public MedicalService getServiceById(Long idService) {
        MedicalService medicalService = medicalServiceRepository.getServiceById(idService);
        return medicalService;
    }
}
