package com.example.docnote.controller;

import com.example.docnote.model.DTO.AppointmentAddDTO;
import com.example.docnote.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable Long id,  Model model){
        if(!model.containsAttribute("appointmentAddDTO")){
            model.addAttribute("appointmentAddDTO", new AppointmentAddDTO());
        }
        if(!model.containsAttribute("appointmentService")){
            model.addAttribute("appointmentService", appointmentService);
        }
        return "add-appointment";
    }
    @PostMapping("/add/{id}")
    public String add(@PathVariable Long id, @Valid AppointmentAddDTO appointmentAddDTO, BindingResult bindingResult, RedirectAttributes rAtt){
        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("appointmentAddDTO", appointmentAddDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addAppointment", bindingResult);
            return "redirect:/appointment/add";
        }
        appointmentService.add(appointmentAddDTO, id);
        return "redirect:/patient/{id}";
    }

}