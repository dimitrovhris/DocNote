package com.app.docnote.web;

import com.app.docnote.model.entity.Patient;
import com.app.docnote.service.PatientService;
import com.app.docnote.service.SicknessLeaveDocumentService;
import com.app.docnote.model.DTO.DocumentAddDTO;
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
@RequestMapping("/document")
public class DocumentController {
    private final PatientService patientService;
    private final SicknessLeaveDocumentService documentService;

    public DocumentController(PatientService patientService, SicknessLeaveDocumentService documentService) {
        this.patientService = patientService;
        this.documentService = documentService;
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable Long id, Model model){
        if(!model.containsAttribute("documentAddDTO")){
            model.addAttribute("documentAddDTO", new DocumentAddDTO());
        }
        Patient patient = patientService.findById(id);
        model.addAttribute("patient", patient);
        return "add-document";
    }
    @PostMapping("/add/{id}")
    public String add(@PathVariable Long id, @Valid DocumentAddDTO documentAddDTO, BindingResult bindingResult, RedirectAttributes rAtt){
        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("documentAddDTO", documentAddDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addDocumentDTO", bindingResult);
            return "redirect:/document/add/{id}";
        }
        documentService.add(documentAddDTO, id);
        return "redirect:/patient/{id}";
    }
}
