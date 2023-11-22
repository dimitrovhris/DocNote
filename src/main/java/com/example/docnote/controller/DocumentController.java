package com.example.docnote.controller;

import com.example.docnote.model.DTO.DocumentAddDTO;
import com.example.docnote.model.entity.Patient;
import com.example.docnote.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/document")
public class DocumentController {
    private final PatientRepository patientRepository;

    public DocumentController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable Long id, Model model){
        if(!model.containsAttribute("documentAddDTO")){
            model.addAttribute("documentAddDTO", new DocumentAddDTO());
        }
        Patient patient = patientRepository.findById(id).get();
        model.addAttribute("patient", patient);
        return "add-document";
    }
}
