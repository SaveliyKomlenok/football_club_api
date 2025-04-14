package com.example.footballclubapi.service;

import com.example.footballclubapi.entity.ClothSize;

import java.util.List;

public interface ClothSizeService {
    ClothSize getById(Long id);
    List<ClothSize> getAll();
    ClothSize save(ClothSize clothSize);
    ClothSize update(ClothSize clothSize);
    void delete(Long id);
}
