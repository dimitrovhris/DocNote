package com.example.docnote.controller;

import com.example.docnote.model.DTO.UserRegisterDTO;

import com.example.docnote.model.entity.UserEntity;

import com.example.docnote.repository.UserRepository;

import com.example.docnote.service.UserService;
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
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    boolean isLoginSuccessful = true;

    public UserController(UserService userService, UserRepository userRepository, CurrentUser currentUser) {
        this.userService = userService;
        this.userRepository = userRepository;

    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("doctorRegisterDTO")) {
            model.addAttribute("doctorRegisterDTO", new UserRegisterDTO());
        }
        if (!model.containsAttribute("doctorService")) {
            model.addAttribute("doctorService", userService);
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO,
                           BindingResult bindingResult,
                           RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()
                || !userService.confirmPassword(userRegisterDTO)
                || userService.containsUsername(userRegisterDTO)
                || userService.containsEmail(userRegisterDTO)
                || userService.containsPhone(userRegisterDTO)) {
            rAtt.addFlashAttribute("doctorRegisterDTO", userRegisterDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.doctorRegisterDTO", bindingResult);
            return "redirect:/user/register";
        }
        userService.register(userRegisterDTO);
        UserEntity user = userRepository.findFirstByEmail(userRegisterDTO.getEmail()).get();
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
