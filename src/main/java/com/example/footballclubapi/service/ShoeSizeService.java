package com.example.footballclubapi.service;

import com.example.footballclubapi.entity.ShoeSize;

import java.util.List;

public interface ShoeSizeService {
    ShoeSize getById(Long id);
    List<ShoeSize> getAll();
    ShoeSize save(ShoeSize shoeSize);
    ShoeSize update(ShoeSize shoeSize);
    void delete(Long id);
}
