package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.entity.ShoesType;
import com.example.footballclubapi.exception.ShoesTypeNotExistsException;
import com.example.footballclubapi.repository.ShoesTypeRepository;
import com.example.footballclubapi.service.ShoesTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.footballclubapi.util.ExceptionMessages.SHOES_TYPE_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class ShoesTypeServiceImpl implements ShoesTypeService {

    private final ShoesTypeRepository shoesTypeRepository;

    @Override
    public ShoesType getById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public List<ShoesType> getAll() {
        return shoesTypeRepository.findAll();
    }

    @Override
    public ShoesType save(ShoesType shoesType) {
        return shoesTypeRepository.save(shoesType);
    }

    @Override
    public ShoesType update(ShoesType shoesType) {
        getOrThrow(shoesType.getId());
        return shoesTypeRepository.save(shoesType);
    }

    @Override
    public void delete(Long id) {
        getOrThrow(id);
        shoesTypeRepository.deleteById(id);
    }

    private ShoesType getOrThrow(Long id) {
        return shoesTypeRepository.findById(id)
                .orElseThrow(() -> new ShoesTypeNotExistsException(String.format(SHOES_TYPE_NOT_EXISTS, id)));
    }
}
