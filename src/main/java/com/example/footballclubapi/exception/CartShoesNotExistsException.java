package com.example.footballclubapi.exception;

public class CartShoesNotExistsException extends RuntimeException {
    public CartShoesNotExistsException(String message) {
        super(message);
    }
}
