package com.example.kantor.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeAdvice {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public String employeeNotFoundException(EmployeeNotFoundException exception, Model model){
        model.addAttribute("errorMessage", exception.getMessage());

        return "error/not_found";
    }

}
