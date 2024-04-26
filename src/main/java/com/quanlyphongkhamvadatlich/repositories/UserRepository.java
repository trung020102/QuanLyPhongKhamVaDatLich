package com.quanlyphongkhamvadatlich.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quanlyphongkhamvadatlich.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
