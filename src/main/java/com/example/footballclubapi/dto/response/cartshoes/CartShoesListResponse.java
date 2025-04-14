package com.example.footballclubapi.dto.response.cartshoes;

import lombok.Builder;

import java.util.List;

@Builder
public record CartShoesListResponse(
        List<CartShoesResponse> items
) {
}
