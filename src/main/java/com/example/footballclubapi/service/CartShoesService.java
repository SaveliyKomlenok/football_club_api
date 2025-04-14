package com.example.footballclubapi.service;

import com.example.footballclubapi.entity.CartShoes;

import java.util.List;

public interface CartShoesService {
    CartShoes getById(Long id);
    List<CartShoes> getAll(Long userId);
    CartShoes save(CartShoes cartShoes);
    CartShoes update(CartShoes cartShoes);
    void delete(Long id);
    void deleteCartByUserId(Long userId);
}
