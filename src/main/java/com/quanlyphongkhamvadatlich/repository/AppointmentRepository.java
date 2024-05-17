package com.quanlyphongkhamvadatlich.repository;


import com.quanlyphongkhamvadatlich.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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


public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    // Tìm kiếm cuộc hẹn theo ID
    Optional<Appointment> findById(Long id);

    // Tìm kiếm các cuộc hẹn trong một ngày cụ thể
    List<Appointment> findByAppointmentDate(Date appointmentDate);
    Page<List<Appointment>> findByAppointmentDate(Date appointmentDate, Pageable pageable);

    // Thêm mới một cuộc hẹn
    Appointment save(Appointment appointment);

    // Cập nhật thông tin của một cuộc hẹn
    Appointment saveAndFlush(Appointment appointment);

    // Xóa một cuộc hẹn dựa trên ID
    void deleteById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Appointment a SET a.status.id = :appointmentStatusId WHERE a.id = :appointmentId")
    void updateAppointmentStatus(Long appointmentId, Long appointmentStatusId);


    public Optional<Appointment> getAppointmentById(Long id);

    public List<Appointment> findByAppointmentDateAndAppointmentShift(Date appointmentDate, String appointmentShift);

    @Query(value = "SELECT new com.quanlyphongkhamvadatlich.dto.client.DisableAppointmentDTO(a.appointmentDate) FROM Appointment a GROUP BY a.appointmentDate HAVING COUNT(a.id) >= 60")
    public List<DisableAppointmentDTO> getAllDisableAppointment();

    @Query("SELECT new com.quanlyphongkhamvadatlich.dto.client.AutoSchedulerEmailNotifierDTO(u.email, a.orderNumber, p.id, p.name, p.phone, a.appointmentDate, a.appointmentShift) FROM Appointment a JOIN a.patient p JOIN p.user u where a.appointmentDate = :appointmentDate AND a.status.id = :statusId")
    List<AutoSchedulerEmailNotifierDTO> findByAppointmentDateAndStatusId(@Param("appointmentDate") Date appointmentDate, @Param("statusId") int statusId);

}
