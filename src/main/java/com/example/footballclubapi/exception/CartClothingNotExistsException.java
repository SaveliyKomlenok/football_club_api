package com.example.footballclubapi.exception;

public class CartClothingNotExistsException extends RuntimeException {
    public CartClothingNotExistsException(String message) {
        super(message);
    }
}
