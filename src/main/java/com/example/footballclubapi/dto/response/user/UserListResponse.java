package com.example.footballclubapi.dto.response.user;

import lombok.Builder;

import java.util.List;

@Builder
public record UserListResponse(
        List<UserResponse> items
) {
}
