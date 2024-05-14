package com.quanlyphongkhamvadatlich.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "appointment_status")
public class AppointmentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status_name")
    private String statusName;

//    @OneToMany(mappedBy = "appointmentStatus", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Appointment> appointmentStatus;

    // Constructors, Getters, and Setters
}