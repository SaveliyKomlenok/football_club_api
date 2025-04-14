package com.example.footballclubapi.service;

import com.example.footballclubapi.entity.Shoes;

import java.util.List;

public interface ShoesService {
    Shoes getById(Long id);
    List<Shoes> getAll();
    Shoes save(Shoes shoes);
    Shoes update(Shoes shoes);
    void delete(Long id);
    String getImagePath(Long id);
}
