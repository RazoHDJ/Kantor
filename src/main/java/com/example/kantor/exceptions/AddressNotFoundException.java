package com.example.kantor.exceptions;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Integer id){
        super("Nie znaleziono adresu o id: " + id);
    }

}
