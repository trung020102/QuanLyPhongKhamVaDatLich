package com.quanlyphongkhamvadatlich.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PrescriptionDetail")
@Getter
@Setter
public class PrescriptionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_detail_id")
    private Long Id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;

    @Column(name = "dosage")
    private String dosage;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit")
    private String unit;



}
