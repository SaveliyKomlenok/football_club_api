package com.example.footballclubapi.dto.request.user;

public record UserAuthenticateRequest(
        String username,
        String password
) {
}
