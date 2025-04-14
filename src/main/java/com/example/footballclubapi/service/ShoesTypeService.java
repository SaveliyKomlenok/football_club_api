package com.example.footballclubapi.service;

import com.example.footballclubapi.entity.ShoesType;

import java.util.List;

public interface ShoesTypeService {
    ShoesType getById(Long id);
    List<ShoesType> getAll();
    ShoesType save(ShoesType shoesType);
    ShoesType update(ShoesType shoesType);
    void delete(Long id);
}
