package com.quanlyphongkhamvadatlich.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "medicines")
@Getter
@Setter
public class Medicine extends BaseEntity{
    @Id
    @Column(name = "medicine_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "medicine_name")
    private String medicine_name;

    @Column(name = "description")
    private String description;
}
