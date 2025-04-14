package com.example.footballclubapi.service;

import com.example.footballclubapi.dto.response.cart.CartResponse;

import java.math.BigDecimal;

public interface CartService {
    CartResponse getAll(Long userId);
    BigDecimal getTotalPrice(Long userId);
}
