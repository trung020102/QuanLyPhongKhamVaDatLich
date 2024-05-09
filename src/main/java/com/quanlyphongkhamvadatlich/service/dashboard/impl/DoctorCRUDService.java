package com.quanlyphongkhamvadatlich.service.dashboard.impl;

import com.quanlyphongkhamvadatlich.entity.Doctor;
import com.quanlyphongkhamvadatlich.repository.DoctorRepository;
import com.quanlyphongkhamvadatlich.service.dashboard.DoctorCRUDServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DoctorCRUDService implements DoctorCRUDServiceInterface {

    @Autowired
    private final DoctorRepository doctorRepository;

    public DoctorCRUDService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    //Save operation
    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    //Read operation
    @Override
    public List<Doctor> fetchDoctorList() {
        return doctorRepository.findAll();
    }

    //Update operation
    @Override
    public Doctor updateDoctor(Doctor doctor, Long id) {
        Doctor doctor1 = doctorRepository.findById(id).get();

        if(Objects.nonNull(doctor.getUsername()) && !"".equalsIgnoreCase(doctor.getUsername())){
            doctor1.setUsername(doctor.getUsername());
        }

        if(Objects.nonNull(doctor.getAvatar()) && !"".equalsIgnoreCase(doctor.getAvatar())){
            doctor1.setAvatar(doctor.getAvatar());
        }

        if(Objects.nonNull(doctor.getSpecialty()) && !"".equalsIgnoreCase(doctor.getSpecialty())){
            doctor1.setSpecialty(doctor.getSpecialty());
        }

        if(Objects.nonNull(doctor.getDiploma()) && !"".equalsIgnoreCase(doctor.getDiploma())){
            doctor1.setDiploma(doctor.getDiploma());
        }

        if(Objects.nonNull(doctor.getWorkplace()) && !"".equalsIgnoreCase(doctor.getWorkplace())){
            doctor1.setWorkplace(doctor.getWorkplace());
        }

        if(Objects.nonNull(doctor.getIntroduction()) && !"".equalsIgnoreCase(doctor.getIntroduction())){
            doctor1.setIntroduction(doctor.getIntroduction());
        }
        return doctorRepository.save(doctor1);
    }

    //Delete operation
    @Override
    public void deleteDoctorById(Long id) {
        doctorRepository.deleteById(id);
    }
}
