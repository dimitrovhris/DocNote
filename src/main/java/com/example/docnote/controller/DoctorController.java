package com.example.docnote.controller;

import com.example.docnote.model.DTO.DoctorLoginDTO;
import com.example.docnote.model.DTO.DoctorRegisterDTO;
import com.example.docnote.model.entity.Doctor;
import com.example.docnote.repository.DoctorRepository;
import com.example.docnote.service.DoctorService;
import com.example.docnote.util.CurrentUser;
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
    boolean isLoginSuccessful = true;

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
        currentUser.setLogged(true);
        Doctor doctor = doctorRepository.findFirstByEmail(doctorRegisterDTO.getEmail()).get();
        currentUser.setDoctor(doctor);
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
        if (!model.containsAttribute("isLoginSuccessful")) {
            model.addAttribute("isLoginSuccessful", isLoginSuccessful);
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid DoctorLoginDTO doctorLoginDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
        isLoginSuccessful = doctorService.validLogin(doctorLoginDTO);
        if (!bindingResult.hasErrors() && isLoginSuccessful) {
            currentUser.setLogged(true);
            Doctor doctor = doctorRepository.findFirstByEmailOrUsername(doctorLoginDTO.getEmailOrUsername(), doctorLoginDTO.getEmailOrUsername()).get();
            currentUser.setDoctor(doctor);
            return "redirect:/home";
        } else{
            rAtt.addFlashAttribute("doctorLoginDTO", doctorLoginDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.doctorLoginDTO", bindingResult);
            return "redirect:/user/login";
        }
    }

    @GetMapping("/logout")
    public String logout(){
        currentUser.setLogged(false);
        return "index";
    }

}
