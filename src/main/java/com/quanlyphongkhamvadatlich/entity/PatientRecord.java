package com.quanlyphongkhamvadatlich.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "PatientRecord")
public class PatientRecord extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_record_id")
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name = "diagnosis")
    private String diagnosis;

    @JsonManagedReference
    @OneToMany(mappedBy = "patientRecord", fetch = FetchType.EAGER)
    private List<ServiceDetail> serviceDetails;

    @OneToOne(mappedBy = "patientRecord")
    private Prescription prescription;


}