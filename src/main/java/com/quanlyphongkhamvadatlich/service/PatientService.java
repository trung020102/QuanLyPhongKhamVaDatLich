package com.quanlyphongkhamvadatlich.service;

import com.quanlyphongkhamvadatlich.entity.*;
import com.quanlyphongkhamvadatlich.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient getPatientById(Long idPatient) {
        Patient patient = patientRepository.getPatientById(idPatient);
        return patient;
    }
    public List<Patient> getPatientByUserId(Long id){
        return patientRepository.getPatientByUserId(id);
    }

}
