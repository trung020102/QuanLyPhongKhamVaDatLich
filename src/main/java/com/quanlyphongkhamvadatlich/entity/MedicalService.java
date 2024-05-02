package com.quanlyphongkhamvadatlich.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name="medical_service")
public class MedicalService {
    @Id
    @Column(name="medical_service_id")
    private Long id;
    @Column(name = "service_name")
    private String serviceName;

    @Column(name= "description", columnDefinition = "TEXT" )
    private String description;

    @Column(name = "price")
    private BigDecimal price;

}
