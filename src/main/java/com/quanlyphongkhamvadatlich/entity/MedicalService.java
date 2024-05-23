package com.quanlyphongkhamvadatlich.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="medical_services")
public class MedicalService extends BaseEntity{
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
