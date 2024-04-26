package com.quanlyphongkhamvadatlich.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "address", length = 255, nullable = true)
    private String address;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "gender", nullable = true)
    private Boolean gender;

    @Column(name = "image", length = 255, nullable = true)
    private String image;

    @Column(name = "username", length = 255, nullable = true)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "phone", length = 255, nullable = true)
    private String phone;

    @Column(name = "status")
    private Boolean status = false;

    @Column(name = "token", nullable = true)
    private String token;

    @Column(name = "token_expiration_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tokenExpirationTime;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Role> roles;
}
