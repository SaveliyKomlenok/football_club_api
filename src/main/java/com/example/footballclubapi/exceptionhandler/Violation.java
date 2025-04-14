package com.example.footballclubapi.exceptionhandler;

public record Violation(
        String fieldName,
        String message) {
}
