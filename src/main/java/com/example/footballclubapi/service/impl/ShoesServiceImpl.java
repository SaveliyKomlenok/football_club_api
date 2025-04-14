package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.entity.Shoes;
import com.example.footballclubapi.exception.ShoesNotExistsException;
import com.example.footballclubapi.repository.ShoesRepository;
import com.example.footballclubapi.service.ShoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.footballclubapi.util.ExceptionMessages.SHOES_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class ShoesServiceImpl implements ShoesService {

    private final ShoesRepository shoesRepository;

    @Override
    public Shoes getById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public List<Shoes> getAll() {
        return shoesRepository.findAll();
    }

    @Transactional
    @Override
    public Shoes save(Shoes shoes) {
        return shoesRepository.save(shoes);
    }

    @Transactional
    @Override
    public Shoes update(Shoes shoes) {
        getOrThrow(shoes.getId());
        return shoesRepository.save(shoes);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        getOrThrow(id);
        shoesRepository.deleteById(id);
    }

    @Override
    public String getImagePath(Long id) {
        return getOrThrow(id).getImagePath();
    }

    private Shoes getOrThrow(Long id) {
        return shoesRepository.findById(id)
                .orElseThrow(() -> new ShoesNotExistsException(String.format(SHOES_NOT_EXISTS, id)));
    }
}
