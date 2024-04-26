package com.quanlyphongkhamvadatlich.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @Column(name="doctor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doctor_name")
    private String username;

    @Column(name = "specialty")
    private String specialty;

    @Column(name= "diploma")
    private String diploma;

    @Column(name = "workplace")
    private String workplace;

    @Column(name = "introduction", columnDefinition = "TEXT")
    private String introduction;
}
