package com.example.footballclubapi.service;

import com.example.footballclubapi.entity.CartClothing;

import java.util.List;

public interface CartClothingService {
    CartClothing getById(Long id);
    List<CartClothing> getAll(Long userId);
    CartClothing save(CartClothing cartClothing);
    CartClothing update(CartClothing cartClothing);
    void delete(Long id);
    void deleteCartByUserId(Long userId);
}
