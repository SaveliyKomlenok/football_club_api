package com.example.footballclubapi.repository;

import com.example.footballclubapi.entity.CartShoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartShoesRepository extends JpaRepository<CartShoes, Long> {
    List<CartShoes> findCartShoesByUserId(Long userId);

    Optional<CartShoes> findCartShoesByShoesWarehouseIdAndUserId(Long shoesWarehouseId, Long userId);

    void deleteAllByUserId(Long userId);
}
