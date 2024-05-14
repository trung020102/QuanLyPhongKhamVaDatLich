package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    // Tìm kiếm cuộc hẹn theo ID
    Optional<Appointment> findById(Long id);

    // Tìm kiếm các cuộc hẹn trong một ngày cụ thể
    List<Appointment> findByAppointmentDate(Date appointmentDate);
    Page<Appointment> findByAppointmentDate(Date appointmentDate, Pageable pageable);

    // Thêm mới một cuộc hẹn
    Appointment save(Appointment appointment);

    // Cập nhật thông tin của một cuộc hẹn
    Appointment saveAndFlush(Appointment appointment);

    // Xóa một cuộc hẹn dựa trên ID
    void deleteById(Long id);

}
