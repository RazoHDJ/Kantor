package com.example.kantor.exceptions;

public class ExchangeRateNotFound extends RuntimeException {
    public ExchangeRateNotFound(Integer id){
        super("Nie znaleziono kursu wymiany o id: " + id);
    }
}
