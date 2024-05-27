package com.quanlyphongkhamvadatlich.repository;

import java.util.Optional;

import com.quanlyphongkhamvadatlich.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import com.quanlyphongkhamvadatlich.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
    public Optional<User> findByToken(String token);
    public User getCustomerById(Long id);
    public User getByDoctor(Doctor doctor);
}
