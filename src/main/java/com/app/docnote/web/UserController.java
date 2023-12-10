package com.app.docnote.web;

import com.app.docnote.model.DTO.UserRegisterDTO;
import com.app.docnote.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
                || userService.containsUsername(userRegisterDTO)
                || userService.containsEmail(userRegisterDTO)
                || userService.containsEgn(userRegisterDTO)) {
            rAtt.addFlashAttribute("doctorRegisterDTO", userRegisterDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.doctorRegisterDTO", bindingResult);
            return "redirect:/user/register";
        }
        if(!userService.confirmPassword(userRegisterDTO)){
            rAtt.addFlashAttribute("doctorRegisterDTO", userRegisterDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.doctorRegisterDTO", bindingResult);
            return "redirect:/user/register/passwords-do-not-match";
        }
        else{
        userService.register(userRegisterDTO);
        return "redirect:/user/login";
        }
    }
    @GetMapping("/register/passwords-do-not-match")
    public String notMatchPasswordsRegister(Model model){
        model.addAttribute("notConfirmedPassword", true);
        return "register";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/login-error")
    public String login(Model model){
        model.addAttribute("invalidLogin", true);
        return "login";
    }
    @PostMapping("/approve/{id}")
    public String approve(@PathVariable Long id){
        userService.approve(id);
        return "redirect:/manage-website/waiting-registrations";
    }
    @PostMapping("/deny/{id}")
    public String deny(@PathVariable Long id){
        userService.remove(id);
        return "redirect:/manage-website/waiting-registrations";
    }

}
