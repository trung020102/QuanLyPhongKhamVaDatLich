package com.quanlyphongkhamvadatlich.service.dashboard;

import com.quanlyphongkhamvadatlich.entity.Doctor;

import java.util.List;

public interface DoctorCRUDServiceInterface {

    //Save operation
    Doctor saveDoctor(Doctor doctor);

    //Read operation
    List<Doctor> fetchDoctorList();

    //Update operation
    Doctor updateDoctor(Doctor doctor, Long id);

    //Delete operation
    void deleteDoctorById(Long id);
}
