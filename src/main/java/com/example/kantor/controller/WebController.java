package com.example.kantor.controller;

import com.example.kantor.models.Employee;
import com.example.kantor.service.ExchangeRateService;
import com.example.kantor.service.SecurityService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class WebController {

    final private SecurityService securityService;
    final private ExchangeRateService exchangeRateService;

    public WebController(final SecurityService securityService,
                         final ExchangeRateService exchangeRateService) {
        this.securityService = securityService;
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/") // Strona główna
    public String homePage(Authentication authentication, Model model) {

        if (authentication != null) {
            final Optional<Employee> employeeOptional = securityService.getCurrentEmployee(authentication);
            employeeOptional.ifPresent(employee -> {
                model.addAttribute("employee", employee);
                model.addAttribute("isAdmin", authentication.getAuthorities()
                        .stream().anyMatch(roles -> roles.getAuthority().equals("ROLE_ADMIN")));
            });
        }
        model.addAttribute("exchangeList", exchangeRateService.getAllExchangeRate());
        return "index";
    }
}
