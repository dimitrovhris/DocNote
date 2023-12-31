package com.app.docnote.web;

import com.app.docnote.model.DTO.PatientAddDTO;
import com.app.docnote.model.entity.Patient;
import com.app.docnote.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String add(@Valid PatientAddDTO patientAddDTO, BindingResult bindingResult, RedirectAttributes rAtt, Authentication authentication){
        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("patientAddDTO", patientAddDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.patientAddDTO", bindingResult);
            return "redirect:/patient/add";
        }
        String username = authentication.getName();
        patientService.addPatient(patientAddDTO, username);
        return "redirect:/home";
    }

    @GetMapping("/{id}")
    public String showProfile(@PathVariable Long id, Model model){
        Patient patientProfile = patientService.findById(id);
        model.addAttribute("patientProfile", patientProfile);
        return "patient";
    }

    @GetMapping("/{id}-successfully-added-test")
    public String showProfileWithTestMessage(@PathVariable Long id, Model model){
        Patient patientProfile = patientService.findById(id);
        model.addAttribute("patientProfile", patientProfile);
        String testMessage = "Successfully added test!";
        model.addAttribute("testMessage", testMessage);
        model.addAttribute("messageShow", true);
        return "patient";
    }

}
