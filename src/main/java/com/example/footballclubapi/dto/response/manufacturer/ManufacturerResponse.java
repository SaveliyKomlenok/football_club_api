package com.example.footballclubapi.dto.response.manufacturer;

import lombok.Builder;

@Builder
public record ManufacturerResponse(
        Long id,
        String name
) {
}
