package com.quanlyphongkhamvadatlich.service;

import com.quanlyphongkhamvadatlich.entity.Customer;
import com.quanlyphongkhamvadatlich.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CutomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public Customer getCustomerByUserId(Long userId){
        return customerRepository.getCustomerByUserId(userId);
    }
}
