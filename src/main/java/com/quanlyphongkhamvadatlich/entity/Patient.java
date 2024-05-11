package com.quanlyphongkhamvadatlich.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "patient")
public class Patient {
    @Id
    @Column(name="patient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "date_of_birth")
    private Date date_of_birth;

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
    @JoinColumn(name = "user_id")
    private User user;
}
