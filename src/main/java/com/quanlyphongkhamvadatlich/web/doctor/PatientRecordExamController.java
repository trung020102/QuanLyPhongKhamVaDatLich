package com.quanlyphongkhamvadatlich.web.doctor;

import com.quanlyphongkhamvadatlich.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.quanlyphongkhamvadatlich.entity.Patient;

@Controller
@RequestMapping("doctor")
public class PatientRecordExamController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/physical_exam")
    public String createPhysicalExam(@RequestParam(name = "patientId", required = false) String patientId, Model model){
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

        // If patient exists, add it to the model and return the view
        model.addAttribute("patient", patient);
        return "dashboard/doctor/physical_exam";
    }

}
