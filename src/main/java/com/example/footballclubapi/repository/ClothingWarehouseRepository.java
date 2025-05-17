package com.example.footballclubapi.repository;

import com.example.footballclubapi.entity.ClothingWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClothingWarehouseRepository extends JpaRepository<ClothingWarehouse, Long> {
    Optional<ClothingWarehouse> findClothingWarehouseByClothingIdAndSizeId(Long clothingId, Long sizeId);

    List<ClothingWarehouse> findClothingWarehousesByClothingId(Long clothingId);
}
