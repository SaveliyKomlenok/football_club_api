package com.example.footballclubapi.mapper;

import com.example.footballclubapi.dto.request.ordershoes.OrderShoesRequest;
import com.example.footballclubapi.dto.response.ordershoes.OrderShoesResponse;
import com.example.footballclubapi.dto.response.ordershoes.OrderShoesListResponse;
import com.example.footballclubapi.entity.OrderShoes;
import com.example.footballclubapi.service.ShoesWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderShoesMapper {

    private final ShoesWarehouseService shoesWarehouseService;
    private final ShoesWarehouseMapper shoesWarehouseMapper;
    private final OrderMapper orderMapper;

    public OrderShoes toEntity(OrderShoesRequest request) {
        return OrderShoes.builder()
                .amount(request.amount())
                .shoesWarehouse(shoesWarehouseService.getById(request.shoes()))
                .build();
    }

    public OrderShoesResponse toResponse(OrderShoes orderShoes) {
        return OrderShoesResponse.builder()
                .id(orderShoes.getId())
                .amount(orderShoes.getAmount())
                .shoes(shoesWarehouseMapper.toResponse(orderShoes.getShoesWarehouse()))
                .order(orderMapper.toResponse(orderShoes.getOrder()))
                .build();
    }

    public OrderShoesListResponse toListResponse(List<OrderShoes> orderShoesList) {
        List<OrderShoesResponse> responses = orderShoesList.stream()
                .map(this::toResponse)
                .toList();
        return OrderShoesListResponse.builder()
                .items(responses)
                .build();
    }
}
