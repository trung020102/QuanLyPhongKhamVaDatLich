package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.dto.client.AutoSchedulerEmailNotifierDTO;
import com.quanlyphongkhamvadatlich.dto.client.DisableAppointmentDTO;
import com.quanlyphongkhamvadatlich.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    public Optional<Appointment> getAppointmentById(Long id);

    public List<Appointment> findByAppointmentDateAndAppointmentShift(Date appointmentDate, String appointmentShift);

    @Query(value = "SELECT new com.quanlyphongkhamvadatlich.dto.client.DisableAppointmentDTO(a.appointmentDate) FROM Appointment a GROUP BY a.appointmentDate HAVING COUNT(a.id) >= 6")
    public List<DisableAppointmentDTO> getAllDisableAppointment();

    @Query("SELECT new com.quanlyphongkhamvadatlich.dto.client.AutoSchedulerEmailNotifierDTO(u.email, a.orderNumber, p.id, p.name, p.phone, a.appointmentDate, a.appointmentShift) FROM Appointment a JOIN a.patient p JOIN p.user u where a.appointmentDate = :appointmentDate AND a.status.id = :statusId")
    List<AutoSchedulerEmailNotifierDTO> findByAppointmentDateAndStatusId(@Param("appointmentDate") Date appointmentDate, @Param("statusId") int statusId);
}
