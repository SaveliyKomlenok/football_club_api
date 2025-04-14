package com.example.footballclubapi.service;

import com.example.footballclubapi.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll(Long userId);
    Order save(Order order);
    void delete(Long id);
}
