package com.example.kantor.exceptions;


public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("Nie udało sie znaleźć pracownika");
    }
}
