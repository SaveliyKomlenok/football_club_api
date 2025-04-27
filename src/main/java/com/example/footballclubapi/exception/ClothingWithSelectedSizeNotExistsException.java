package com.example.footballclubapi.exception;

public class ClothingWithSelectedSizeNotExistsException extends RuntimeException {
    public ClothingWithSelectedSizeNotExistsException(String message) {
        super(message);
    }
}
