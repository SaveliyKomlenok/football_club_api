package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.dto.response.cart.CartResponse;
import com.example.footballclubapi.entity.User;
import com.example.footballclubapi.mapper.CartMapper;
import com.example.footballclubapi.service.CartClothingService;
import com.example.footballclubapi.service.CartService;
import com.example.footballclubapi.service.CartShoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartShoesService cartShoesService;
    private final CartClothingService cartClothingService;
    private final CartMapper cartMapper;

    @Override
    public CartResponse getAll(Long userId) {
        return cartMapper.toResponse(
                cartClothingService.getAll(userId),
                cartShoesService.getAll(userId),
                getTotalPrice(userId)
        );
    }

    @Override
    public BigDecimal getTotalPrice(Long userId) {
        return getTotalClothingPrice(userId).add(getTotalShoesPrice(userId))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal getTotalClothingPrice(Long userId) {
        return cartClothingService.getAll(userId).stream()
                .map(cartClothing -> cartClothing.getClothingWarehouse().getClothing().getPrice().multiply(BigDecimal.valueOf(cartClothing.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getTotalShoesPrice(Long userId) {
        return cartShoesService.getAll(userId).stream()
                .map(cartShoes -> cartShoes.getShoesWarehouse().getShoes().getPrice().multiply(BigDecimal.valueOf(cartShoes.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}