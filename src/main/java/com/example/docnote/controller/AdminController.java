package com.example.docnote.controller;

import com.example.docnote.model.entity.UserEntity;
import com.example.docnote.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage-website")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String manageWebsite() {
        return "manage-website";
    }

    @GetMapping("/waiting-registrations")
    public String waitingRegistrations(Model model, Authentication authentication) {
        UserEntity user = userService.findFirstByUsername(authentication.getName());
        model.addAttribute("user", user);
        model.addAttribute("notApprovedUsers", userService.findNotApproved());

        return "waiting-registrations";
    }

    @GetMapping("/admins")
    public String admins(Model model) {
        model.addAttribute("admins", userService.findAdmins());
        return "admins";
    }

    @PostMapping("/remove-admin/{id}")
    public String removeAdmin(@PathVariable Long id) {
        if (id != 1) userService.removeAsAdmin(id);
        return "redirect:/manage-website/admins";
    }

}
