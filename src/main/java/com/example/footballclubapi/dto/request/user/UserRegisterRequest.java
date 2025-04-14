package com.example.footballclubapi.dto.request.user;

public record UserRegisterRequest(
        String username,
        String firstname,
        String lastname,
        String password
) {
}
