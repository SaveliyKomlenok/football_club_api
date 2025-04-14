package com.example.footballclubapi.dto.request.user;

public record UserEditRequest(
        String firstname,
        String lastname,
        String username
) {
}
