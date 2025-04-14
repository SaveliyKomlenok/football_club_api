package com.example.footballclubapi.exception;

public class AmountOfShoesExceededException extends RuntimeException {
    public AmountOfShoesExceededException(String message) {
        super(message);
    }
}
