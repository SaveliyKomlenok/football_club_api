package com.example.footballclubapi.service;

import com.example.footballclubapi.entity.OrderShoes;

import java.util.List;

public interface OrderShoesService {
    List<OrderShoes> getAll(Long orderId);
    OrderShoes save(OrderShoes orderShoes);
    void deleteOrderShoesByOrderId(Long orderId);
}
