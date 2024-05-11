package com.quanlyphongkhamvadatlich.service;

import com.quanlyphongkhamvadatlich.dto.client.PatientDTO;
import com.quanlyphongkhamvadatlich.entity.Patient;
import com.quanlyphongkhamvadatlich.entity.User;
import com.quanlyphongkhamvadatlich.exception.web.PatientAlreadyExistsException;
import com.quanlyphongkhamvadatlich.repository.PatientRepository;
import com.quanlyphongkhamvadatlich.repository.UserRepository;
import com.quanlyphongkhamvadatlich.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Optional<Patient> findByIdAndUser(Long id, User user){return patientRepository.findByIdAndUser(id, user);}
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }
    public Patient addNewPatient(PatientDTO model, User user) {
        Patient newPatient = Patient
                .builder()
                .name(model.getName())
                .phone(model.getPhone())
                .date_of_birth(model.getDate_of_birth())
                .gender(model.getGender())
                .address(model.getAddress())
                .citizen_number(model.getCitizen_number())
                .career(model.getCareer())
                .insurance_number(model.getInsurance_number())
                .user(user)
                .build();

        return patientRepository.save(newPatient);

    }
}
