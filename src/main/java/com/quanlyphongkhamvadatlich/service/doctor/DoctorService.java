package com.quanlyphongkhamvadatlich.service.doctor;

import com.quanlyphongkhamvadatlich.dto.dashboard.DoctorResister;
import com.quanlyphongkhamvadatlich.entity.Doctor;
import com.quanlyphongkhamvadatlich.entity.Role;
import com.quanlyphongkhamvadatlich.entity.User;
import com.quanlyphongkhamvadatlich.mapper.DoctorMapper;
import com.quanlyphongkhamvadatlich.repository.DoctorRepository;
import com.quanlyphongkhamvadatlich.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class DoctorService {
    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createDoctor(DoctorResister doctorResister){
        Doctor newDoctor = new Doctor();
        newDoctor.setUsername(doctorResister.getUsername());
        newDoctor.setSpecialty(doctorResister.getSpecialty());
        newDoctor = doctorRepository.save(newDoctor);

        User newUser = new User();

        newUser.setUsername(doctorResister.getUsername());
        newUser.setEmail(doctorResister.getEmail());
        newUser.setPassword(passwordEncoder.encode(doctorResister.getPassword()));
        newUser.setDoctor(newDoctor);
        newUser.setRole(new Role(3L));
        newUser.setStatus(true);
        userRepository.save(newUser);
    }
}
