package com.example.footballclubapi.service;

import com.example.footballclubapi.entity.OrderClothing;

import java.util.List;

public interface OrderClothingService {
    List<OrderClothing> getAll(Long orderId);
    OrderClothing save(OrderClothing orderClothing);
    void deleteOrderClothingByOrderId(Long orderId);
}
