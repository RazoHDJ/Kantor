package com.example.kantor.exceptions;

public class UserCouldNotBeAdded extends RuntimeException {
    public UserCouldNotBeAdded() {
        super("Nie udało się dodać użytkownika");
    }
}
