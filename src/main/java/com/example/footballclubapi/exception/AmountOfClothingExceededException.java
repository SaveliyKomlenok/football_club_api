package com.example.footballclubapi.exception;

public class AmountOfClothingExceededException extends RuntimeException {
    public AmountOfClothingExceededException(String message) {
        super(message);
    }
}
