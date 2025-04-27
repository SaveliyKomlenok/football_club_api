package com.example.footballclubapi.mapper;

import com.example.footballclubapi.dto.request.cartclothing.CartClothingRequest;
import com.example.footballclubapi.dto.response.cartclothing.CartClothingListResponse;
import com.example.footballclubapi.dto.response.cartclothing.CartClothingResponse;
import com.example.footballclubapi.entity.CartClothing;
import com.example.footballclubapi.entity.ClothingWarehouse;
import com.example.footballclubapi.entity.User;
import com.example.footballclubapi.service.ClothingWarehouseService;
import com.example.footballclubapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartClothingMapper {
    private final ClothingWarehouseService clothingWarehouseService;
    private final ClothingWarehouseMapper clothingWarehouseMapper;
    private final UserService userService;

    public CartClothing toEntity(CartClothingRequest request, Long userId) {
        ClothingWarehouse warehouse = clothingWarehouseService.getByClothingIdAndSizeId(request.clothing(), request.size());
        User user = userService.getById(userId);
        return CartClothing.builder()
                .amount(request.amount())
                .user(user)
                .clothingWarehouse(warehouse)
                .build();
    }

    public CartClothing toEntity(Long id, CartClothingRequest request, Long userId) {
        CartClothing cartClothing = toEntity(request, userId);
        cartClothing.setId(id);
        return cartClothing;
    }

    public CartClothingResponse toResponse(CartClothing cartClothing) {
        return CartClothingResponse.builder()
                .id(cartClothing.getId())
                .amount(cartClothing.getAmount())
                .clothing(clothingWarehouseMapper.toResponse(cartClothing.getClothingWarehouse()))
                .build();
    }

    public CartClothingListResponse toListResponse(List<CartClothing> cartClothingList) {
        List<CartClothingResponse> responses = cartClothingList.stream()
                .map(this::toResponse)
                .toList();
        return CartClothingListResponse.builder()
                .items(responses)
                .build();
    }
}
