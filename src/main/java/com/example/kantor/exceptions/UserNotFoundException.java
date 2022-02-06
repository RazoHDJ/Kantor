package com.example.kantor.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("Nie znaleziono użytkownika o id: " + id);
    }
}
