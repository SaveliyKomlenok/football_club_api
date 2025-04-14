package com.example.footballclubapi.service;

import com.example.footballclubapi.entity.Clothing;

import java.util.List;

public interface ClothingService {
    Clothing getById(Long id);
    List<Clothing> getAll();
    Clothing save(Clothing clothing);
    Clothing update(Clothing clothing);
    void delete(Long id);
    String getImagePath(Long id);
}
