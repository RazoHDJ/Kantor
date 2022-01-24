package com.example.kantor.controller;

import com.example.kantor.dto.NewExchangeRateDTO;
import com.example.kantor.service.ExchangeRateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exchange_rate")
public class ExchangeRateController {

    final private ExchangeRateService exchangeRateService;

    public ExchangeRateController(final ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("")
    public String addExchangeRateForm(Model model) {
        model.addAttribute("newExchangeRate", new NewExchangeRateDTO());

        return "exchange/add_exchange_rate";
    }

    @PostMapping("")
    public String addExchangeRate(@ModelAttribute NewExchangeRateDTO newExchange) {
        exchangeRateService.addNewExchange(newExchange);
        return "redirect:/";
    }

    @PostMapping("/{id}")
    public String updateExchangeRate(@ModelAttribute NewExchangeRateDTO newExchange, @PathVariable Integer id) {
        exchangeRateService.updateExchange(id, newExchange);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteExchangeRate(@PathVariable Integer id) {
        exchangeRateService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String editExchangeRate(@PathVariable Integer id, Model model) {
        exchangeRateService.getExchangeRate(id).ifPresent(exchangeRate -> model.addAttribute("updateExchangeRate",
                NewExchangeRateDTO.builder()
                        .fullName(exchangeRate.getCurrency().getFullName())
                        .shortName(exchangeRate.getCurrency().getShortName())
                        .amount(exchangeRate.getCurrency().getAmount().toString())
                        .value(exchangeRate.getValue().toString())
                        .build()));
        model.addAttribute("id", id);

        return "exchange/edit_exchange_rate";
    }

}
