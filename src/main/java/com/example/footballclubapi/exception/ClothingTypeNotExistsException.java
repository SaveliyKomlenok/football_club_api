package com.example.footballclubapi.exception;

public class ClothingTypeNotExistsException extends RuntimeException {
    public ClothingTypeNotExistsException(String message) {
        super(message);
    }
}
