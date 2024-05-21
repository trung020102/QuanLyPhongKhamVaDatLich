package com.quanlyphongkhamvadatlich.service.dashboard;

import com.quanlyphongkhamvadatlich.dto.dashboard.DoctorServiceParam;
import com.quanlyphongkhamvadatlich.dto.dashboard.DoctorServiceResult;
import com.quanlyphongkhamvadatlich.entity.Doctor;
import com.quanlyphongkhamvadatlich.mapper.DoctorServiceMapper;
import com.quanlyphongkhamvadatlich.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DoctorCRUDService{

    private final DoctorServiceMapper  doctorServiceMapper;
    private final DoctorRepository doctorRepository;

    //Save operation
    public Doctor createDoctor(DoctorServiceParam doctorServiceParam) {
        Doctor newDoctor = doctorServiceMapper.toEntity(doctorServiceParam);
        return doctorRepository.save(newDoctor);
    }

    //Read operation
    public List<DoctorServiceResult> fetchDoctorList() {
        List<Doctor> doctorList = doctorRepository.findAll();
        return doctorList.stream().map(doctorServiceMapper::toResult).toList();
    }

    //Update operation
    public void updateDoctor(Long id, DoctorServiceParam doctorServiceParam) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor != null) {
            Doctor newDoctor = doctorServiceMapper.toEntity(doctorServiceParam);
            newDoctor.setId(doctor.getId());
            doctorRepository.save(newDoctor);
        }
    }

    //Delete operation
    public void deleteDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow();
        doctorRepository.delete(doctor);
    }
}
