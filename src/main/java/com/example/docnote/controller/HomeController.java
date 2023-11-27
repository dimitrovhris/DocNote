package com.example.docnote.controller;

import com.example.docnote.model.entity.UserEntity;
import com.example.docnote.repository.UserRepository;
import org.springframework.security.core.Authentication;
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
    public String home(Model model, Authentication authentication){
        model.addAttribute("userRepository", userRepository);
        UserEntity user = userRepository.findFirstByUsername(authentication.getName()).get();
        model.addAttribute("user", user);

        return "home";
    }

}
