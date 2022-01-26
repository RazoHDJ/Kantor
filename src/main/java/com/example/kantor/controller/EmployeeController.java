package com.example.kantor.controller;

import com.example.kantor.service.SecurityService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    final private SecurityService securityService;

    public EmployeeController(final SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("") // profil pracownika
    public String userProfile(Authentication authentication, Model model) {
        securityService.getCurrentEmployee(authentication).ifPresent(employee -> model.addAttribute("employee", employee));

        return "employee/employee";
    }


}