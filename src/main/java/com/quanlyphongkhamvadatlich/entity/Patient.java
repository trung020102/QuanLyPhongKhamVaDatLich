package com.quanlyphongkhamvadatlich.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "patients")
public class Patient extends BaseEntity {


    @Id
    @Column(name="patient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "gender")
    private String gender;


    @Column(name = "phone")
    private String phone;

    @Column(name = "date_of_birth")
    private Date birthday;

    @Column(name = "gender")
    private Boolean gender;


    @Column(name = "address")
    private String address;

    @Column(name = "citizen_number")
    private String citizenNumber;


    @Column(name = "career")
    private String career;


//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @OneToMany(mappedBy = "patient")
//    private List<Appointment> appointments;
//
//    // Mối quan hệ với entity PatientRecord
//    @OneToMany(mappedBy = "patient")
//    private List<PatientRecord> patientRecords;
//
//    // Constructors, Getters, and Setters
//}
//=======
    @Column(name = "insurance_number")
    private String insurance_number;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private List<Appointment> appointments;

    @JsonManagedReference
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private List<PatientRecord> patientRecords;
}

