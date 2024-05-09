package com.quanlyphongkhamvadatlich.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient_records")
public class PatientRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign Key
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // Foreign Key
//    @ManyToOne
//    @JoinColumn(name = "doctor_id")
//    private Doctor doctor;

    private String symptoms;
    private String diagnosis;

    // Constructors, getters, setters
}
