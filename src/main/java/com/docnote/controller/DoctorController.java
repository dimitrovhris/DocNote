package com.docnote.controller;

import com.docnote.model.DTO.DoctorLoginDTO;
import com.docnote.model.DTO.DoctorRegisterDTO;
import com.docnote.model.entity.Doctor;
import com.docnote.repository.DoctorRepository;
import com.docnote.service.DoctorService;
import com.docnote.util.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorRepository doctorRepository;
    private final CurrentUser currentUser;

    public DoctorController(DoctorService doctorService, DoctorRepository doctorRepository, CurrentUser currentUser) {
        this.doctorService = doctorService;
        this.doctorRepository = doctorRepository;
        this.currentUser = currentUser;

    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("doctorRegisterDTO")) {
            model.addAttribute("doctorRegisterDTO", new DoctorRegisterDTO());
        }
        if (!model.containsAttribute("doctorService")) {
            model.addAttribute("doctorService", doctorService);
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid DoctorRegisterDTO doctorRegisterDTO,
                           BindingResult bindingResult,
                           RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()
                || !doctorService.confirmPassword(doctorRegisterDTO)
                || doctorService.containsUsername(doctorRegisterDTO)
                || doctorService.containsEmail(doctorRegisterDTO)
                || doctorService.containsPhone(doctorRegisterDTO)) {
            rAtt.addFlashAttribute("doctorRegisterDTO", doctorRegisterDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.doctorRegisterDTO", bindingResult);
            return "redirect:/user/register";
        }
        doctorService.register(doctorRegisterDTO);
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("doctorLoginDTO")) {
            model.addAttribute("doctorLoginDTO", new DoctorLoginDTO());
        }
        if (!model.containsAttribute("doctorService")) {
            model.addAttribute("doctorService", doctorService);
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(DoctorLoginDTO doctorLoginDTO) {
        if (doctorService.validLogin(doctorLoginDTO)) {
            currentUser.setLogged(true);
            Doctor doctor = doctorRepository.findFirstByEmail(doctorLoginDTO.getEmailOrUsername()).get();
            currentUser.setDoctor(doctor);
            return "redirect:/home";
        }
        return "redirect:/user/login";
    }
}
