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
@Table(name = "appointments")
public class Appointment extends BaseEntity{
    @Id
    @Column(name = "appointment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "patient_id")
    private Patient patient;

    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Column(name = "appointment_shift")
    private String appointmentShift;

    @Column(name = "symptom")
    private String symptom;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "order_number")
    private Integer orderNumber;


}
