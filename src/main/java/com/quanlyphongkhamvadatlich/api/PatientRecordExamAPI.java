package com.quanlyphongkhamvadatlich.api;

import com.quanlyphongkhamvadatlich.dto.dashboard.ListPatientRecordDTO;
import com.quanlyphongkhamvadatlich.dto.dashboard.MedicalServiceDTO;
import com.quanlyphongkhamvadatlich.dto.dashboard.PrescriptionDetailDTO;
import com.quanlyphongkhamvadatlich.entity.*;
import com.quanlyphongkhamvadatlich.security.UserPrincipal;
import com.quanlyphongkhamvadatlich.service.*;
import com.quanlyphongkhamvadatlich.service.dashboard.MedicalServiceBusiness;
import com.quanlyphongkhamvadatlich.service.dashboard.ServiceDetailBusiness;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctor/physical_exam")
public class PatientRecordExamAPI {

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalServiceBusiness medicalServiceBusiness;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PatientRecordService patientRecordService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PrescriptionDetailService prescriptionDetailService;

    @Autowired
    private ServiceDetailBusiness serviceDetailBusiness;

    @PostMapping("/{id}")
    public ResponseEntity<Map<String, Object>> createPhysicalExam(@PathVariable(value = "id") String id,@Valid @RequestBody ListPatientRecordDTO listPatientRecord) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) auth.getPrincipal();
        User user = userDetails.getUser();
        Doctor doctor = user.getDoctor();

        Patient patient = patientService.getPatientById(Long.parseLong(id));
        if (patient == null || doctor == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Invalid patient or doctor information."));
        }

        PatientRecord patientRecord = new PatientRecord();
        patientRecord.setPatient(patient);
        patientRecord.setDoctor(doctor);
        patientRecord.setDiagnosis(listPatientRecord.getDiagnosis());
        patientRecord.setNote(listPatientRecord.getNote());
        PatientRecord savedPatientRecord = patientRecordService.save(patientRecord);
        BigDecimal totalFee = BigDecimal.ZERO;
        Prescription prescription = new Prescription();
        prescription.setPatientRecord(savedPatientRecord);
        Prescription savedPrescription = prescriptionService.save(prescription);
        List<MedicalServiceDTO> listService = listPatientRecord.getListService();
        for (MedicalServiceDTO detail : listService) {
            ServiceDetail serviceDetail = new ServiceDetail();
            MedicalService medicalService = medicalServiceBusiness.getServiceById(detail.getServiceId());
            if (medicalService != null) {
                serviceDetail.setPatientRecord(savedPatientRecord);
                serviceDetail.setMedicalService(medicalService);
                serviceDetailBusiness.save(serviceDetail);
                totalFee = totalFee.add(medicalService.getPrice());
            }
        }
        List<PrescriptionDetailDTO> listPrescription = listPatientRecord.getListPrescription();
        for (PrescriptionDetailDTO detail : listPrescription) {
            PrescriptionDetail prescriptionDetail = new PrescriptionDetail();
            Medicine medicine = medicineService.getMedicineById(detail.getMedicineId());
            if (medicine != null) {
                prescriptionDetail.setPrescription(savedPrescription);
                prescriptionDetail.setMedicine(medicine);
                prescriptionDetail.setDosage(detail.getDosage());
                prescriptionDetail.setQuantity(detail.getQuantity());
                prescriptionDetail.setUnit(detail.getUnit());
                prescriptionDetailService.save(prescriptionDetail);
            }
        }
        savedPatientRecord.setTotalFees(totalFee);
        patientRecordService.save(savedPatientRecord);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Patient record has been added successfully.");
        response.put("redirectUrl", "http://localhost:8082/doctor/appointments");
        return ResponseEntity.ok(response);
    }
}
