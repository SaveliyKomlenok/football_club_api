package com.example.footballclubapi.dto.response.shoestype;

import lombok.Builder;

@Builder
public record ShoesTypeResponse(
        Long id,
        String name
) {
}
