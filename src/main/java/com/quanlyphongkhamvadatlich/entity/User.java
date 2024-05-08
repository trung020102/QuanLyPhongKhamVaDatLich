package com.quanlyphongkhamvadatlich.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends BaseEntity{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name= "token")
    private String token;

    @Column(name = "token_expiration_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tokenExpirationTime;

    @Column(name="avatar")
    private String avatar;

    @Column(name = "status")
    private Boolean status = false;

    @OneToOne(mappedBy = "user")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
