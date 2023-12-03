package com.app.docnote.web;

import com.app.docnote.model.entity.UserEntity;
import com.app.docnote.service.UserService;
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
        userService.removeAsAdmin(id);
        return "redirect:/manage-website/admins";
    }
    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("notAdmins", userService.findNotAdmins());
        return "users";
    }

    @PostMapping("/add-admin/{id}")
    public String addAdmin(@PathVariable Long id){
        userService.addAdmin(id);
        return "redirect:/manage-website/users-successfully-added-admin";
    }
    @GetMapping("/users-successfully-added-admin")
    public String usersAfterAddingAdmin(Model model){
        model.addAttribute("notAdmins", userService.findNotAdmins());
        model.addAttribute("addedAdmin", true);
        return "users";
    }
    @PostMapping("/remove-user/{id}")
    public String removeUser(@PathVariable Long id){
        userService.remove(id);
        return "redirect:/manage-website/users-successfully-removed-user";
    }
    @GetMapping("/users-successfully-removed-user")
    public String usersAfterRemoving(Model model){
        model.addAttribute("removedUser", true);
        return "users";
    }

}
