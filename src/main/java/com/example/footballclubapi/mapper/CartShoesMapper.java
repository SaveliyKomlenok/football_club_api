package com.example.footballclubapi.mapper;

import com.example.footballclubapi.dto.request.cartshoes.CartShoesRequest;
import com.example.footballclubapi.dto.response.cartshoes.CartShoesListResponse;
import com.example.footballclubapi.dto.response.cartshoes.CartShoesResponse;
import com.example.footballclubapi.entity.CartShoes;
import com.example.footballclubapi.entity.ShoesWarehouse;
import com.example.footballclubapi.entity.User;
import com.example.footballclubapi.service.ShoesWarehouseService;
import com.example.footballclubapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartShoesMapper {
    private final ShoesWarehouseService shoesWarehouseService;
    private final ShoesWarehouseMapper shoesWarehouseMapper;
    private final UserService userService;

    public CartShoes toEntity(CartShoesRequest request, Long userId) {
        ShoesWarehouse warehouse = shoesWarehouseService.getByShoesIdAndSizeId(request.shoes(), request.size());
        User user = userService.getById(userId);
        return CartShoes.builder()
                .amount(request.amount())
                .user(user)
                .shoesWarehouse(warehouse)
                .build();
    }

    public CartShoes toEntity(Long id, CartShoesRequest request, Long userId) {
        CartShoes cartShoes = toEntity(request, userId);
        cartShoes.setId(id);
        return cartShoes;
    }

    public CartShoesResponse toResponse(CartShoes cartShoes) {
        return CartShoesResponse.builder()
                .id(cartShoes.getId())
                .amount(cartShoes.getAmount())
                .shoes(shoesWarehouseMapper.toResponse(cartShoes.getShoesWarehouse()))
                .build();
    }

    public CartShoesListResponse toListResponse(List<CartShoes> cartShoesList) {
        List<CartShoesResponse> responses = cartShoesList.stream()
                .map(this::toResponse)
                .toList();
        return CartShoesListResponse.builder()
                .items(responses)
                .build();
    }
}