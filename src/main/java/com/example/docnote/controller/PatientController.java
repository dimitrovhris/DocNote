package com.example.docnote.controller;

import com.example.docnote.model.DTO.PatientAddDTO;
import com.example.docnote.model.entity.Patient;
import com.example.docnote.repository.PatientRepository;
import com.example.docnote.service.PatientService;
import com.example.docnote.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;
    private final CurrentUser currentUser;
    private final PatientRepository patientRepository;

    public PatientController(PatientService patientService, CurrentUser currentUser, PatientRepository patientRepository) {
        this.patientService = patientService;
        this.currentUser = currentUser;
        this.patientRepository = patientRepository;
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
    @GetMapping("/{id}")
    public String showProfile(@PathVariable Long id, Model model){
        Patient patientProfile = patientRepository.findById(id).get();
        model.addAttribute("patientProfile", patientProfile);
        return "patient";
    }

}
