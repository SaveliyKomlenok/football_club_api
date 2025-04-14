package com.example.footballclubapi.dto.response.shoeswarehouse;

import lombok.Builder;

import java.util.List;

@Builder
public record ShoesWarehouseListResponse(
        List<ShoesWarehouseResponse> items
) {
}
