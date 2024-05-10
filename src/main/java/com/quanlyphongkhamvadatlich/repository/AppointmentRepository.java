package com.quanlyphongkhamvadatlich.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quanlyphongkhamvadatlich.dto.client.AutoSchedulerEmailNotifierDTO;
import com.quanlyphongkhamvadatlich.entity.Appointment;

import java.util.Date;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT new com.quanlyphongkhamvadatlich.dto.client.AutoSchedulerEmailNotifierDTO(u.email, a.orderNumber, p.id, p.name, p.phone, a.appointmentDate, a.appointmentShift) FROM Appointment a JOIN a.patient p JOIN p.user u where a.appointmentDate = :appointmentDate AND a.status.id = :statusId")
    List<AutoSchedulerEmailNotifierDTO> findByAppointmentDateAndStatusId(@Param("appointmentDate") Date appointmentDate, @Param("statusId") int statusId);
    
}
