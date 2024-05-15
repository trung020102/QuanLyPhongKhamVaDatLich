package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AppointmentStatusRepository extends JpaRepository<AppointmentStatus, Long> {
}