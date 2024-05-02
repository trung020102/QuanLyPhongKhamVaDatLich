package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.entity.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long> {

}
