package com.example.footballclubapi.mapper;

import com.example.footballclubapi.dto.request.order.OrderRequest;
import com.example.footballclubapi.dto.response.order.OrderListResponse;
import com.example.footballclubapi.dto.response.order.OrderResponse;
import com.example.footballclubapi.entity.Order;
import com.example.footballclubapi.service.OrderClothingService;
import com.example.footballclubapi.service.OrderShoesService;
import com.example.footballclubapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final UserService userService;
    private final UserMapper userMapper;
    private final OrderClothingMapper orderClothingMapper;
    private final OrderShoesMapper orderShoesMapper;
    private final OrderClothingService orderClothingService;
    private final OrderShoesService orderShoesService;

    public Order toEntity(OrderRequest request, Long userId) {
        return Order.builder()
                .address(request.address())
                .user(userService.getById(userId))
                .build();
    }

    public OrderResponse toResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .dateOfCreation(order.getDateOfCreation())
                .address(order.getAddress())
                .user(userMapper.toResponse(order.getUser()))
                .totalPrice(order.getTotalPrice())
                .orderClothingList(orderClothingService.getAll(order.getId()).stream()
                        .map(orderClothingMapper::toResponse).toList())
                .orderShoesList(orderShoesService.getAll(order.getId()).stream()
                        .map(orderShoesMapper::toResponse).toList())
                .build();
    }

    public OrderListResponse toListResponse(List<Order> orderList) {
        List<OrderResponse> orderResponseList = orderList.stream()
                .map(this::toResponse)
                .toList();
        return OrderListResponse.builder()
                .items(orderResponseList)
                .build();
    }
}
