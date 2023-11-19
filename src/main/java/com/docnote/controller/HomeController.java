package com.docnote.controller;

import com.docnote.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String home(){
        return "home";
    }
}
