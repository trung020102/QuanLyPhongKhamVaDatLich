package com.quanlyphongkhamvadatlich.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Service")
@Getter
@Setter
public class Service {
    @Id
    @Column(name = "service_id")
    private int Id;
    @Column(name = "service_name")
    private String service_name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @JsonManagedReference
    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER)
    private List<ServiceDetail> serviceDetails;

}
