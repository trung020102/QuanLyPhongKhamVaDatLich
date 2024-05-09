package com.quanlyphongkhamvadatlich.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "prescriptions")
@Getter
@Setter
public class Prescription {
    @Id
    @Column(name = "prescription_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "patient_record_id")
    private PatientRecord patientRecord;


    @JsonManagedReference
    @OneToMany(mappedBy = "prescription", fetch = FetchType.EAGER)
    private List<PrescriptionDetail> prescriptionDetails;
}
