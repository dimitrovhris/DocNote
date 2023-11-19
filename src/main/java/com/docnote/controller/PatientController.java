package com.docnote.controller;

import com.docnote.model.DTO.PatientAddDTO;
import com.docnote.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/add")
    public String add(Model model){
        if (!model.containsAttribute("patientAddDTO")) {
            model.addAttribute("patientAddDTO", new PatientAddDTO());
        }
        if (!model.containsAttribute("patientService")) {
            model.addAttribute("patientService", patientService);
        }
        return "add-patient";
    }
    @PostMapping("/add")
    public String add(@Valid PatientAddDTO patientAddDTO, BindingResult bindingResult, RedirectAttributes rAtt){
        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("patientAddDTO", patientAddDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.patientAddDTO", bindingResult);
            return "redirect:/patient/add";
        }
        patientService.addPatient(patientAddDTO);
        return "redirect:/home";
    }
}
