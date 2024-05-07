package com.quanlyphongkhamvadatlich.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class User extends BaseEntity{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name= "token")
    private String token;

    @Column(name = "token_expiration_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tokenExpirationTime;

    @Column(name="avatar")
    private String avatar;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "profile_id")
    private String profile_id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Patient> patients;

    @OneToMany(mappedBy = "user")
    private List<Appointment> appointments;
}
