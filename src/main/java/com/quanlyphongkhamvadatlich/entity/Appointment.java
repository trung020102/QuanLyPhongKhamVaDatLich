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
@Builder
@Table(name = "appointments")
public class Appointment extends BaseEntity{


    @Column(name = "appointment_date")
    private Date appointmentDate;




    @Id
    @Column(name = "appointment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    // Mối quan hệ một-nhiều với entity EmailAttachment
    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailAttachment> emailAttachments;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "appointment_status_id")
//    private AppointmentStatus appointmentStatus;

    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Column(name = "appointment_shift")
    private String appointmentShift;

    @Column(name = "symptom")
    private String symptom;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @Column(name = "order_number")
    private Integer orderNumber;
}
