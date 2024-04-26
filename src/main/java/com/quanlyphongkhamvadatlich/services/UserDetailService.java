package com.quanlyphongkhamvadatlich.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quanlyphongkhamvadatlich.entities.User;
import com.quanlyphongkhamvadatlich.entities.UserDetailImpl;
import com.quanlyphongkhamvadatlich.repositories.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        
        if(!user.isPresent()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new UserDetailImpl(user.get());
    }
}
