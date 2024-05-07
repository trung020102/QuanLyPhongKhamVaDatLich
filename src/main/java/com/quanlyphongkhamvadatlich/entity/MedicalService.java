package com.quanlyphongkhamvadatlich.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name="medical_services")
public class MedicalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="medical_service_id")
    private Long id;
    @Column(name = "service_name")
    private String serviceName;

    @Column(name= "description", columnDefinition = "TEXT" )
    private String description;

    @Column(name = "price")
    private BigDecimal price;

}
