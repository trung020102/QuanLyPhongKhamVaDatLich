package com.quanlyphongkhamvadatlich.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "patients")
public class Patient {
    @Id
    @Column(name="patient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "date_of_birth")
    private Date birthday;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "address")
    private String address;

    @Column(name = "citizen_number")
    private String citizen_number;

    @Column(name = "career")
    private String career;

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
