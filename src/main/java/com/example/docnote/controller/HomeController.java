package com.example.docnote.controller;

import com.example.docnote.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final UserRepository userRepository;
    public HomeController( UserRepository userRepository) {
        this.userRepository = userRepository;

    }


    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("userRepository", userRepository.findByApprovedFalse());
        return "home";
    }

}
