package com.quanlyphongkhamvadatlich.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quanlyphongkhamvadatlich.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    
}
