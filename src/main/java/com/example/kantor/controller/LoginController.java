package com.example.kantor.controller;

import com.example.kantor.dto.RegisterUserDTO;
import com.example.kantor.models.User;
import com.example.kantor.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    final private LoginService loginService;

    public LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/process_register")
    public String processRegister(RegisterUserDTO registerUserDTO) {

        User savedUser = loginService.registerUser(registerUserDTO);

        return "index";
    }


}
