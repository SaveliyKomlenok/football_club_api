package com.example.footballclubapi.exception;

public class ShoesNotExistsException extends RuntimeException {
    public ShoesNotExistsException(String message) {
        super(message);
    }
}
