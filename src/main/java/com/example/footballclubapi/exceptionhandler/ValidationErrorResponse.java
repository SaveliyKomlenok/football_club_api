package com.example.footballclubapi.exceptionhandler;

import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) {
}
