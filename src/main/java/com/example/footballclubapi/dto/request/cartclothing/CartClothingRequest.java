package com.example.footballclubapi.dto.request.cartclothing;

public record CartClothingRequest(
        Integer amount,
        Long clothing,
        Long size
) {
}
