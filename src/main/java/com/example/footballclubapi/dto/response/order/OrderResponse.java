package com.example.footballclubapi.dto.response.order;

import com.example.footballclubapi.dto.response.user.UserResponse;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderResponse(
        Long id,
        LocalDateTime dateOfCreation,
        String address,
        UserResponse user
) {
}
