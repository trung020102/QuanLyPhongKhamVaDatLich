package com.quanlyphongkhamvadatlich.service.client.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.quanlyphongkhamvadatlich.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quanlyphongkhamvadatlich.dto.client.RegistrationRequest;
import com.quanlyphongkhamvadatlich.dto.client.UpdatePersonalInforRequest;
import com.quanlyphongkhamvadatlich.entity.Customer;
import com.quanlyphongkhamvadatlich.entity.Role;
import com.quanlyphongkhamvadatlich.entity.User;
import com.quanlyphongkhamvadatlich.enums.EnumRole;
import com.quanlyphongkhamvadatlich.enums.TokenValidationResult;
import com.quanlyphongkhamvadatlich.exception.web.UserAlreadyExistsException;
import com.quanlyphongkhamvadatlich.repository.RoleRepository;
import com.quanlyphongkhamvadatlich.repository.UserRepository;
import com.quanlyphongkhamvadatlich.service.client.IUserService;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegistrationRequest request) {
        Optional<User> user = this.findByEmail(request.getEmail());

        if (user.isPresent()) {
            throw new UserAlreadyExistsException(
                    "User with email " + request.getEmail() + " already exists");
        }

        User newUser = User
                .builder()
                .email(request.getEmail())
                .username(request.getUserName())
                .password(encoder.encode(request.getPassword()))
                .status(false)
                .build();

        Optional<Role> roleForClient = roleRepository.findByName(EnumRole.CLIENT.name());

        if (roleForClient.isPresent()) {
            newUser.setRole(roleForClient.get());
        }

        // set customer infor
        Customer customer = new Customer();
        customer.setName(request.getFullName());
        customerRepository.save(customer);
        newUser.setCustomer(customer);

        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUserVerificationToken(User user, String verificationToken) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveUserVerificationToken'");
    }

    @Override
    public Optional<User> findByToken(String token) {
        return userRepository.findByToken(token);
    }

    @Override
    public TokenValidationResult validateToken(String token) {
        Optional<User> userOptional = userRepository.findByToken(token);

        if (userOptional.isEmpty()) {
            return TokenValidationResult.TOKEN_NOT_FOUND;
        }
    
        User user = userOptional.get();
        
        if (user.getStatus()) {
            return TokenValidationResult.USER_ALREADY_ACTIVATED;
        }
    
        Date currentTime = new Date();
        Date expirationTime = user.getTokenExpirationTime();
    
        if (currentTime.after(expirationTime)) {
            return TokenValidationResult.TOKEN_EXPIRED;
        }
    
        user.setStatus(true);
        userRepository.save(user);
    
        return TokenValidationResult.USER_ACTIVATED_SUCCESSFULLY;
    }

    @Override
    public Optional<User> updateToken(String oldToken) {
        Optional<User> user = userRepository.findByToken(oldToken);

        if (user.isPresent()) {
            var userUpdate = user.get();
            userUpdate.setToken(UUID.randomUUID().toString());
            userUpdate.setTokenExpirationTime(getTokenExpirationTime());
            userRepository.save(userUpdate);
        }

        return user;
    }

    private Date getTokenExpirationTime() {
        Date currentTime = new Date();
        long timeToAdd = 15 * 60 * 1000; // milliseconds

        return new Date(currentTime.getTime() + timeToAdd);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updatePersonalInfor(User user, UpdatePersonalInforRequest request) {
        Customer userInfor = user.getCustomer();
        // update common user info
        user.setEmail(request.getEmail());
        // update the profile infor
        userInfor.setAddress(request.getAddress());
        userInfor.setGender(request.getGender());
        userInfor.setName(request.getFullName());
        userInfor.setPhone(request.getPhone());
        user.setCustomer(userInfor);
        customerRepository.save(userInfor);
        return userRepository.save(user);
    }
    public User getCustomerByCustomerId(Long customerId){
        return userRepository.getInformationByCustomerId(customerId);
    }

    public void changePassword(User user, String newPassword){
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

    }
    @Override
    public boolean oldPasswordIsValid(User user, String oldPassword){
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

}
