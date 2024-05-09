package com.quanlyphongkhamvadatlich.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Column(name = "time_slot")
    private String timeSlot;

    @Column(name = "order_number")
    private Integer orderNumber;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    // Mối quan hệ một-nhiều với entity EmailAttachment
    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailAttachment> emailAttachments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_status_id")
    private AppointmentStatus appointmentStatus;
}