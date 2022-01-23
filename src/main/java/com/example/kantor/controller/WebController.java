package com.example.kantor.controller;

import com.example.kantor.models.User;
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

        model.addAttribute("isLogged", authentication != null);

        final Optional<User> userOptional = securityService.getCurrentUser(authentication);
        userOptional.ifPresent(user -> model.addAttribute("user", user));

        return "index";
    }

    @GetMapping("/exchange_rates") // Strona wymiany walut
    public String exchangeRate(Model model) {
        model.addAttribute("exchangeList", exchangeRateService.getAllExchangeRate());
        return "exchange_rates";
    }


}
