package com.quanlyphongkhamvadatlich.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Status")
public class Status {
    @Id
    @Column(name = "status_id")
    private int id;

    @Column(name = "name")
    private String name;

}
