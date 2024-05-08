package com.quanlyphongkhamvadatlich.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Medicine")
@Getter
@Setter
public class Medicine {
    @Id
    @Column(name = "medicine_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "medicine_name")
    private String medicine_name;

    @Column(name = "description")
    private String description;
}
