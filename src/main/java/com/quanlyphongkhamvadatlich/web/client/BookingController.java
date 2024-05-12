package com.quanlyphongkhamvadatlich.web.client;

import com.quanlyphongkhamvadatlich.dto.client.AppointmentDTO;
import com.quanlyphongkhamvadatlich.dto.client.DisableAppointmentDTO;
import com.quanlyphongkhamvadatlich.dto.client.PatientDTO;
import com.quanlyphongkhamvadatlich.entity.Appointment;
import com.quanlyphongkhamvadatlich.entity.Patient;
import com.quanlyphongkhamvadatlich.security.UserPrincipal;
import com.quanlyphongkhamvadatlich.service.AppointmentService;
import com.quanlyphongkhamvadatlich.service.PatientService;
import com.quanlyphongkhamvadatlich.service.StatusService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/client")
@RequiredArgsConstructor
public class BookingController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final StatusService statusService;


    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/booking")
    public String booking(Model model) {
        model.addAttribute("patient", new Patient());
        return "client/pages/booking";
    }

    @PostMapping("/booking/oldpatient")
    public String oldPatientBooking(@RequestParam(name = "patient_id") Long patient_id, Model model, Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Optional<Patient> patient = patientService.findByIdAndUser(patient_id, userPrincipal.getUser());
        if (patient.isPresent()) {
            Long patientId = patient.get().getId();
            System.out.println(patient_id);
            return "redirect:/client/booking/appointment?patientID=" + patientId;
        }
        model.addAttribute("error", "Mã số bệnh nhân chưa có trong dữ liệu của chúng tôi !");
        model.addAttribute("patient", new Patient());
        return "client/pages/booking";
    }

    @PostMapping("/booking/newpatient")
    public String newPatientBooking(@ModelAttribute("patient") @Valid PatientDTO request, BindingResult bindingResult, Model model, Authentication authentication) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("newPatientTab", "actived");
            return "client/pages/booking";
        }

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Patient newPatient = patientService.addNewPatient(request, userPrincipal.getUser());

        model.addAttribute("patient_id", newPatient.getId());
        model.addAttribute("patient_name", newPatient.getName());
        model.addAttribute("formSubmitted", true);
        return "client/pages/booking";
    }

    @GetMapping("/booking/appointment")
    public String appointment(@RequestParam(name = "patientID") Long patientID, Model model) {
        model.addAttribute("appointment", new AppointmentDTO(patientID));
        System.out.println("Giá trị ID ở đây là: " + patientID);
        return "client/pages/appointment";
    }

    @PostMapping("/booking/appointment")
    public String makeAppointment(@ModelAttribute("appointment") @Valid AppointmentDTO request,Model model, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            return "client/pages/appointment";
        }

        List<Appointment> list = appointmentService.getAppointmentByDateAndShift(request.getAppointmentDate(),request.getAppointmentShift());
        if(list.size() >= 5){
            String shiftText = "";
            switch(request.getAppointmentShift()) {
                case "sang":
                    shiftText = "Sáng (7h - 11h)";
                    break;
                case "chieu":
                    shiftText = "Chiều (13h - 17h)";
                    break;
                case "toi":
                    shiftText = "Tối (17h30 - 21h)";
                    break;
                default:
                    shiftText = request.getAppointmentShift();
            }
            model.addAttribute("fullBooking", "Ca " + shiftText + " của ngày hôm nay đã hết lượt đặt. Vui lòng chọn ca khám khác hoặc ngày khám khác");
            return "client/pages/appointment";
        }
        System.out.println("list này là: " + list.size());

        int statusId = statusService.findById(1).get().getId();
        Optional<Patient> patient = patientService.findById(request.getPatientId());


        Appointment appointment = appointmentService.bookAppointment(request, patient, statusId);
        return "redirect:/client/booking/appointment/success?patientId=" + request.getPatientId() + "&appointmentId=" + appointment.getId();

    }

    @GetMapping("/booking/appointment/success")
    public String success(Model model, @RequestParam(name = "appointmentId") Long appointmentId,
                          @RequestParam(name = "patientId") Long patientId, Authentication authentication) throws MessagingException {

        Optional<Appointment> appointmentOptional = appointmentService.getAppointmentById(appointmentId);

        if (appointmentOptional.isPresent()) {
            model.addAttribute("modelView", new AppointmentDTO(appointmentId, patientId));
            Appointment appointment = appointmentOptional.get();
            Patient patient = appointment.getPatient();

            model.addAttribute("IdOfPatient",patientId);
            System.out.println("giá trị id này: " + patientId);
            model.addAttribute("orderNumber", appointment.getOrderNumber());
            model.addAttribute("name", patient.getName());
            model.addAttribute("phone", patient.getPhone());
            model.addAttribute("appointmentDate", appointment.getAppointmentDate());
            model.addAttribute("appointmentShift", appointment.getAppointmentShift());
            model.addAttribute("symptom", appointment.getSymptom());

            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            appointmentService.sendAppointmentInfo(userPrincipal.getEmail(),
                    appointment.getOrderNumber(),patientId,patient.getName(),patient.getPhone(),
                    appointment.getAppointmentDate(),appointment.getAppointmentShift());
            return "client/pages/success-booking";
        }
        return "redirect:/client/booking";
    }


}