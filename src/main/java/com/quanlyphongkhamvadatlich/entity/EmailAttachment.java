package com.quanlyphongkhamvadatlich.entity;


import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "email_attachments")
public class EmailAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mối quan hệ với entity Appointment
    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    // Trường để lưu tên file hoặc đường dẫn file
    private String fileName;
}
