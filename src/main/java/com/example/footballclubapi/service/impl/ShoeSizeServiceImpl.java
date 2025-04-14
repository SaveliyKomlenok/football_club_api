package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.entity.ShoeSize;
import com.example.footballclubapi.exception.ShoeSizeNotExistsException;
import com.example.footballclubapi.repository.ShoeSizeRepository;
import com.example.footballclubapi.service.ShoeSizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.footballclubapi.util.ExceptionMessages.SHOE_SIZE_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class ShoeSizeServiceImpl implements ShoeSizeService {

    private final ShoeSizeRepository shoeSizeRepository;

    @Override
    public ShoeSize getById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public List<ShoeSize> getAll() {
        return shoeSizeRepository.findAll();
    }

    @Override
    public ShoeSize save(ShoeSize shoeSize) {
        return shoeSizeRepository.save(shoeSize);
    }

    @Override
    public ShoeSize update(ShoeSize shoeSize) {
        getOrThrow(shoeSize.getId());
        return shoeSizeRepository.save(shoeSize);
    }

    @Override
    public void delete(Long id) {
        getOrThrow(id);
        shoeSizeRepository.deleteById(id);
    }

    private ShoeSize getOrThrow(Long id) {
        return shoeSizeRepository.findById(id)
                .orElseThrow(() -> new ShoeSizeNotExistsException(String.format(SHOE_SIZE_NOT_EXISTS, id)));
    }
}
