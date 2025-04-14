package com.example.footballclubapi.dto.response.ordershoes;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderShoesListResponse(
        List<OrderShoesResponse> items
) {
}
