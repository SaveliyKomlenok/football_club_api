package com.example.footballclubapi.service;

import com.example.footballclubapi.entity.ClothingType;

import java.util.List;

public interface ClothingTypeService {
    ClothingType getById(Long id);
    List<ClothingType> getAll();
    ClothingType save(ClothingType clothingType);
    ClothingType update(ClothingType clothingType);
    void delete(Long id);
}
