package com.quanlyphongkhamvadatlich.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="medical_service")
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

    @JsonManagedReference
    @OneToMany(mappedBy = "medicalService", fetch = FetchType.EAGER)
    private List<ServiceDetail> serviceDetails;

}
