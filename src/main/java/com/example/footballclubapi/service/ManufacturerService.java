package com.example.footballclubapi.service;

import com.example.footballclubapi.entity.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    Manufacturer getById(Long id);
    List<Manufacturer> getAll();
    Manufacturer save(Manufacturer manufacturer);
    Manufacturer update(Manufacturer manufacturer);
    void delete(Long id);
}
