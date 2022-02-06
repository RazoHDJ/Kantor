package com.example.kantor.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFoundHandler(UserNotFoundException exception, Model model){
        model.addAttribute("errorMessage", exception.getMessage());

        return "error/not_found";
    }

    @ExceptionHandler(UserCouldNotBeAdded.class)
    public String userCouldNotBeAdded(UserCouldNotBeAdded ex, Model model){
        model.addAttribute("errorMessage", ex.getMessage());

        return "error/not_found";
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public String addressNotFoundException(AddressNotFoundException ex, Model model){
        model.addAttribute("errorMessage", ex.getMessage());

        return "error/not_found";
    }

}
