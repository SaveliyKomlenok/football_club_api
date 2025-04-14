package com.example.footballclubapi.exception;

public class ClothingNotExistsException extends RuntimeException {
    public ClothingNotExistsException(String message) {
        super(message);
    }
}
