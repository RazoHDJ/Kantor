package com.example.kantor.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExchangeAdvice {

    @ExceptionHandler(ExchangeRateNotFound.class)
    public String exchangeRateNotFound(ExchangeRateNotFound exception, Model model){
        model.addAttribute("errorMessage", exception.getMessage());

        return "error/not_found";
    }

}
