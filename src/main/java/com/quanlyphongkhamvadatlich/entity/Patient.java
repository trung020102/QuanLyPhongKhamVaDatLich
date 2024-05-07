package com.quanlyphongkhamvadatlich.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "citizen_number")
    private String citizenNumber;

    @Column(name = "career")
    private String career;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    // Mối quan hệ với entity PatientRecord
    @OneToMany(mappedBy = "patient")
    private List<PatientRecord> patientRecords;

    // Constructors, Getters, and Setters
}