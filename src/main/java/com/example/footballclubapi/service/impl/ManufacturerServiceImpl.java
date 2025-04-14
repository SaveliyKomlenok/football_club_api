package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.entity.Manufacturer;
import com.example.footballclubapi.exception.ManufacturerNotExistsException;
import com.example.footballclubapi.repository.ManufacturerRepository;
import com.example.footballclubapi.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.footballclubapi.util.ExceptionMessages.MANUFACTURER_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    @Override
    public Manufacturer getById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public List<Manufacturer> getAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        getOrThrow(manufacturer.getId());
        return manufacturerRepository.save(manufacturer);
    }

    @Override
    public void delete(Long id) {
        getOrThrow(id);
        manufacturerRepository.deleteById(id);
    }

    private Manufacturer getOrThrow(Long id) {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new ManufacturerNotExistsException(String.format(MANUFACTURER_NOT_EXISTS, id)));
    }
}
