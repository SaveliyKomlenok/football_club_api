package com.example.footballclubapi.exception;

public class ClothingWarehouseNotExistsException extends RuntimeException {
    public ClothingWarehouseNotExistsException(String message) {
        super(message);
    }
}
