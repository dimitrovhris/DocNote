package com.example.docnote.controller;

import com.example.docnote.model.DTO.AppointmentAddDTO;
import com.example.docnote.model.entity.Appointment;
import com.example.docnote.model.entity.Patient;
import com.example.docnote.service.AppointmentService;
import com.example.docnote.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final PatientService patientService;

    public AppointmentController(AppointmentService appointmentService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable Long id,  Model model){
        if(!model.containsAttribute("appointmentAddDTO")){
            model.addAttribute("appointmentAddDTO", new AppointmentAddDTO());
        }
        if(!model.containsAttribute("appointmentService")){
            model.addAttribute("appointmentService", appointmentService);
        }
        model.addAttribute("patient", patientService.findById(id));
        return "add-appointment";
    }
    @PostMapping("/add/{id}")
    public String add(Model model, @PathVariable Long id, @Valid AppointmentAddDTO appointmentAddDTO, BindingResult bindingResult, RedirectAttributes rAtt){
        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("appointmentAddDTO", appointmentAddDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addAppointment", bindingResult);
            return "redirect:/appointment/add/{id}";
        }
        appointmentService.add(appointmentAddDTO, id);
        Patient patientProfile = patientService.findById(id);
        model.addAttribute("patient", patientProfile);
        if(appointmentAddDTO.isWorker()){
            return "redirect:/document/add/{id}";
        }
        return "redirect:/patient/{id}";
    }
    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Long id){
        Appointment appointment = appointmentService.findById(id);
        model.addAttribute("appointment", appointment);
        return "appointment-info";
    }

}
