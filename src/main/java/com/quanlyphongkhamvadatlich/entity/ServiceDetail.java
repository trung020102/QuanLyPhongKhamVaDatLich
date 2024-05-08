package com.quanlyphongkhamvadatlich.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ServiceDetail")
public class ServiceDetail {
    @Id
    @Column(name = "servicedetail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "patient_record_id")
    private PatientRecord patientRecord;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "medical_service_id", nullable = false)
    private MedicalService medicalService;
}
