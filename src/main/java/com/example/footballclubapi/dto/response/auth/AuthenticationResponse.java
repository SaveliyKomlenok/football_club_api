package com.example.footballclubapi.dto.response.auth;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token,
        String role,
        String username
) {
}
