package com.example.kantor.controller;

import com.example.kantor.dto.RegisterUserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String helloWorld() {
        return "index";
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registerUserDTO", new RegisterUserDTO());

        return "register";
    }


}
