package com.example.footballclubapi.mapper;

import com.example.footballclubapi.dto.request.orderclothing.OrderClothingRequest;
import com.example.footballclubapi.dto.response.orderclothing.OrderClothingResponse;
import com.example.footballclubapi.dto.response.orderclothing.OrderClothingListResponse;
import com.example.footballclubapi.entity.OrderClothing;
import com.example.footballclubapi.service.ClothingWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderClothingMapper {
    private final ClothingWarehouseService clothingWarehouseService;
    private final ClothingWarehouseMapper clothingWarehouseMapper;
    private final OrderMapper orderMapper;

    public OrderClothing toEntity(OrderClothingRequest request) {
        return OrderClothing.builder()
                .amount(request.amount())
                .clothingWarehouse(clothingWarehouseService.getById(request.clothing()))
                .build();
    }

    public OrderClothingResponse toResponse(OrderClothing orderClothing) {
        return OrderClothingResponse.builder()
                .id(orderClothing.getId())
                .amount(orderClothing.getAmount())
                .clothing(clothingWarehouseMapper.toResponse(orderClothing.getClothingWarehouse()))
                .order(orderMapper.toResponse(orderClothing.getOrder()))
                .build();
    }

    public OrderClothingListResponse toListResponse(List<OrderClothing> orderClothingList) {
        List<OrderClothingResponse> responses = orderClothingList.stream()
                .map(this::toResponse)
                .toList();
        return OrderClothingListResponse.builder()
                .items(responses)
                .build();
    }
}
