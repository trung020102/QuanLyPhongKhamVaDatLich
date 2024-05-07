package com.quanlyphongkhamvadatlich.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name="customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name= "gender")
    private Boolean gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name")
    private String name;
}
