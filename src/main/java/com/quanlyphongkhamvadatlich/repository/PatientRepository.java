package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.entity.Patient;
import com.quanlyphongkhamvadatlich.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    public Optional<Patient> findById(Long id);
    public Optional<Patient> findByIdAndUser(Long id, User user);
    Patient getPatientById(Long patientId);
    List<Patient> getPatientByUserId(Long id);

}
