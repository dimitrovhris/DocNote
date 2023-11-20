package com.example.docnote.controller;

import com.example.docnote.model.entity.Patient;
import com.example.docnote.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    public HomeController(CurrentUser currentUser) {
        this.currentUser = currentUser;

    }


    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("currentUser",currentUser);
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){
        List<Patient> patients = currentUser.getDoctor().getPatients();
        model.addAttribute("patients", patients);
        return "home";
    }

}
