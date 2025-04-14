package com.example.footballclubapi.repository;

import com.example.footballclubapi.entity.CartClothing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartClothingRepository extends JpaRepository<CartClothing, Long> {
    List<CartClothing> findCartClothingByUserId(Long userId);

    Optional<CartClothing> findCartClothingByClothingWarehouseIdAndUserId(Long clothingWarehouseId, Long userId);

    void deleteAllByUserId(Long userId);
}
