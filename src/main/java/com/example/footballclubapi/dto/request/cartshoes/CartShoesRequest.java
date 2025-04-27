package com.example.footballclubapi.dto.request.cartshoes;

public record CartShoesRequest(
        Integer amount,
        Long shoes,
        Long size
) {
}
