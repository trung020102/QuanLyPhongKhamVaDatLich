package com.quanlyphongkhamvadatlich.web.doctor;

import com.quanlyphongkhamvadatlich.entity.*;
import com.quanlyphongkhamvadatlich.security.UserPrincipal;
import com.quanlyphongkhamvadatlich.service.MedicineService;
import com.quanlyphongkhamvadatlich.service.PatientRecordService;
import com.quanlyphongkhamvadatlich.service.PatientService;
import com.quanlyphongkhamvadatlich.service.dashboard.MedicalServiceBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("doctor")
public class PatientRecordExamController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private MedicalServiceBusiness medicalServiceBusiness;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PatientRecordService patientRecordService;


    @GetMapping("/physical_exam")
    public String createPhysicalExam(@RequestParam(name = "patientId", required = false) String patientId, Model model){
        List<MedicalService> medical_services = medicalServiceBusiness.getMedicalService();
        model.addAttribute("medical_services", medical_services);

        List<Medicine> medicines = medicineService.getMedicine();
        model.addAttribute("medicines", medicines);
        if(patientId == null || patientId.trim().isEmpty()){
            model.addAttribute("Message", "Mã bệnh nhân là bắt buộc.");
            return "dashboard/doctor/physical_exam";
        }

        Patient patient = patientService.getPatientById(Long.parseLong(patientId));


        if(patient == null){
            model.addAttribute("errorMessage", "Không tìm thấy bệnh nhân.");
            return "dashboard/doctor/physical_exam";
        }
        String value = patientId;
        model.addAttribute("value", value);

        model.addAttribute("patient", patient);

        return "dashboard/doctor/physical_exam";
    }
    @PostMapping("/physical_exam")
    public String savePhysicalExam(@RequestParam(name = "patientId", required = false) String patientId, @RequestParam(name = "diagnosis", required = false) String diagnosis) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) auth.getPrincipal();
        User user = userDetails.getUser();
        Doctor doctor = user.getDoctor();

        Patient patient = patientService.getPatientById(Long.parseLong(patientId));
        if (patient == null || doctor == null) {
           //Xử lý
            return null;
        }

        PatientRecord patientRecord = new PatientRecord();
        patientRecord.setPatient(patient);
        patientRecord.setDoctor(doctor);
        patientRecord.setDiagnosis(diagnosis);
       /* patientRecord.setServiceDetails(serviceDetails);*/

        PatientRecord patientRecord1= patientRecordService.save(patientRecord);
        return "dashboard/doctor/physical_exam";
    }

}
