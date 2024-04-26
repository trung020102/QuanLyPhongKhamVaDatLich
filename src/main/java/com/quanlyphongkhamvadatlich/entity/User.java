package com.quanlyphongkhamvadatlich.entity;

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

    @Column(name = "password")
    private String password;

    @Column(name= "token")
    private String token;

    @Column(name="avatar")
    private String avatar;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "profile_id")
    private String profile_id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
