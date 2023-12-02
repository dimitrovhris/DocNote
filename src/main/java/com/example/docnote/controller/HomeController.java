package com.example.docnote.controller;

import com.example.docnote.model.entity.UserEntity;
import com.example.docnote.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final UserService userService;
    public HomeController( UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model, Authentication authentication){
        UserEntity user = userService.findFirstByUsername(authentication.getName());
        model.addAttribute("user", user);
        return "home";
    }

}
