package com.quanlyphongkhamvadatlich.api;

import com.quanlyphongkhamvadatlich.dto.dashboard.DoctorServiceParam;
import com.quanlyphongkhamvadatlich.service.dashboard.DoctorCRUDService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/doctors")
@RequiredArgsConstructor
public class DoctorCRUDAPI {
    private DoctorCRUDService doctorCRUDService;

    //Save Operation
    @PostMapping()
    public ResponseEntity<?> createDoctor(
            @Valid @RequestBody DoctorServiceParam doctorServiceParam){
        doctorCRUDService.createDoctor(doctorServiceParam);
        return ResponseEntity.ok().build();
    }

    //Read operation
    @GetMapping()
    public ResponseEntity<?> getAllDoctors(){
        return ResponseEntity.ok(doctorCRUDService.fetchDoctorList());
    }

    //Update operation
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id,
                                          @Valid @RequestBody DoctorServiceParam doctorServiceParam){
        doctorCRUDService.updateDoctor(id, doctorServiceParam);
        return ResponseEntity.ok().build();
    }

    //Delete operation
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDoctor(@PathVariable Long id){
        doctorCRUDService.deleteDoctorById(id);
        return ResponseEntity.ok().build();
    }
}
