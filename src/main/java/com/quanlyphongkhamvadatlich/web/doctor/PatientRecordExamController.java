package com.quanlyphongkhamvadatlich.web.doctor;

import com.quanlyphongkhamvadatlich.entity.*;
import com.quanlyphongkhamvadatlich.service.*;
import com.quanlyphongkhamvadatlich.service.dashboard.MedicalServiceBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class PatientRecordExamController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private MedicalServiceBusiness medicalServiceBusiness;

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/physical_exam/{id}")
    public String createPhysicalExam(Model model,  @PathVariable(value = "id") Long id){
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("patient", patient);
        List<MedicalService> medical_services = medicalServiceBusiness.getMedicalService();
        model.addAttribute("medical_services", medical_services);
        List<Medicine> medicines = medicineService.getMedicine();
        model.addAttribute("medicines", medicines);
        return "dashboard/doctor/physical_exam";
    }
}
