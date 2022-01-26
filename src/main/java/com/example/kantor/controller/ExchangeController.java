package com.example.kantor.controller;

import com.example.kantor.dto.ExchangeDTO;
import com.example.kantor.models.Currency;
import com.example.kantor.models.ExchangeRate;
import com.example.kantor.models.User;
import com.example.kantor.service.ExchangeRateService;
import com.example.kantor.service.ExchangeService;
import com.example.kantor.service.SecurityService;
import com.example.kantor.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Controller
@RequestMapping("/exchange")
public class ExchangeController {

    final private UserService userService;
    final private ExchangeRateService exchangeRateService;
    final private ExchangeService exchangeService;
    final private SecurityService securityService;

    public ExchangeController(final UserService userService,
                              final ExchangeRateService exchangeRateService,
                              final ExchangeService exchangeService,
                              final SecurityService securityService) {
        this.userService = userService;
        this.exchangeRateService = exchangeRateService;
        this.exchangeService = exchangeService;
        this.securityService = securityService;
    }

    @GetMapping("") // ekran obliczania wartości wymiany
    public String exchangeMainPage(Model model) {

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        List<ExchangeRate> exchangeRates = exchangeRateService.getAllExchangeRate();
        model.addAttribute("exchangeRates", exchangeRates);

        model.addAttribute("exchangeDTO", new ExchangeDTO());

        return "exchange/calculate";
    }


    @GetMapping("/all") // historia wymian
    public String showAllExchanges(Model model) {
        model.addAttribute("exchangeList", exchangeService.getAllExchanges());

        return "exchange/exchangeList";
    }

    @PostMapping("")
    public String addNewExchangeToDatabase(@ModelAttribute ExchangeDTO exchangeDTO, Model model, Authentication authentication) {
        BigDecimal fullPrice = exchangeDTO.getFromExchangeRate().getValue()
                .multiply(exchangeDTO.getAmount())
                .divide(exchangeDTO.getToExchangeRate().getValue(), 2, RoundingMode.HALF_UP);
        exchangeDTO.setFullPrice(fullPrice);

        Currency currencyToExchange = exchangeDTO.getToExchangeRate().getCurrency();
        if (fullPrice.compareTo(currencyToExchange.getAmount()) > 0 || authentication == null) {
            model.addAttribute("currencyInStock", currencyToExchange.getAmount());
            model.addAttribute("shortNameCurrencyInStock", currencyToExchange.getShortName());
            model.addAttribute("exchangeValue", fullPrice);

            return "exchange/failed";
        } else {
            securityService.getCurrentEmployee(authentication).ifPresent(employee -> {
                exchangeDTO.setEmployee(employee);
                exchangeService.addNewExchange(exchangeDTO);
            });
            return "exchange/exchangeList";
        }
    }


}
