package com.example.footballclubapi.dto.request.orderclothing;

public record OrderClothingRequest(
        Integer amount,
        Long clothing,
        Long order
) {
}
