package com.app.docnote.web;

import com.app.docnote.model.DTO.TestAddDTO;
import com.app.docnote.service.TestService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/test")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable Long id,  Model model){
        if(!model.containsAttribute("testAddDTO")){
            model.addAttribute("testAddDTO", new TestAddDTO());
            model.addAttribute("id", id);
        }
        return "add-test";
    }
    @PostMapping("/add/{id}")
    public String add(@PathVariable Long id, @Valid TestAddDTO testAddDTO, BindingResult bindingResult, RedirectAttributes rAtt, Authentication authentication){
        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("testAddDTO", testAddDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.testAddDTO");
            return "redirect:/test/add/{id}";
        }
        testService.add(testAddDTO, id);
        return "redirect:/patient/{id}-successfully-added-test";
    }
}
