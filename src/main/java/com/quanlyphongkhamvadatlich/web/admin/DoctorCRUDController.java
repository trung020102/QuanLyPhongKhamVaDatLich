package com.quanlyphongkhamvadatlich.web.admin;

import com.quanlyphongkhamvadatlich.entity.Doctor;
import com.quanlyphongkhamvadatlich.service.dashboard.impl.DoctorCRUDService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorCRUDController {
    @Autowired private DoctorCRUDService doctorCRUDService;

    //Save Operation
    @PostMapping("/admin/doctors")
    public Doctor saveDoctor(
            @Valid @RequestBody Doctor doctor){
        return doctorCRUDService.saveDoctor(doctor);
    }

    //Read operation
    @GetMapping("/admin/doctors")
    public List<Doctor> fetchDoctorList(){
        return doctorCRUDService.fetchDoctorList();
    }

    //Update operation
    @PutMapping("/admin/doctors/{id}")
    public Doctor updateDoctor(@RequestBody Doctor doctor,
                               @PathVariable("id") Long id){
        return doctorCRUDService.updateDoctor(
                doctor, id);
    }

    //Delete operation
    @DeleteMapping("/admin/doctors/{id}")
    public String deleteDoctorById(@PathVariable("id") Long id){

        doctorCRUDService.deleteDoctorById(id);
        return "Deleted successfully";
    }
}
