package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.entity.PatientRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PatientRecordRepository extends JpaRepository<PatientRecord, Long> {
    @Query("FROM PatientRecord pr WHERE DATE(pr.createdAt) BETWEEN :startDate AND :endDate AND pr.patient.id = :patientId")
    List<PatientRecord> getAllBetweenDatesAndPatientId(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("patientId") Long patientId);
}
