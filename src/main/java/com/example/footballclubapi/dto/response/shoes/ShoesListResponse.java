package com.example.footballclubapi.dto.response.shoes;

import lombok.Builder;

import java.util.List;

@Builder
public record ShoesListResponse(
        List<ShoesResponse> items
) {
}
