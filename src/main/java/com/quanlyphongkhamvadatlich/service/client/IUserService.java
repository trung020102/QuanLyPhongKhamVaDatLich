package com.quanlyphongkhamvadatlich.service.client;

import java.util.Optional;

import com.quanlyphongkhamvadatlich.dto.client.RegistrationRequest;
import com.quanlyphongkhamvadatlich.entity.User;
import com.quanlyphongkhamvadatlich.enums.TokenValidationResult;

public interface IUserService {
    User registerUser(RegistrationRequest request);
    Optional<User> findByEmail(String email);
    void saveUserVerificationToken(User user, String verificationToken);
    Optional<User> findByToken(String token);
    TokenValidationResult validateToken(String token);
    Optional<User> updateToken(String oldToken);
    User saveUser(User user);
}
