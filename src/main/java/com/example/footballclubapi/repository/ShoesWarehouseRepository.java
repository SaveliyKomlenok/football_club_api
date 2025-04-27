package com.example.footballclubapi.repository;

import com.example.footballclubapi.entity.ClothingWarehouse;
import com.example.footballclubapi.entity.ShoesWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoesWarehouseRepository extends JpaRepository<ShoesWarehouse, Long> {
    Optional<ShoesWarehouse> findShoesWarehouseByShoesIdAndSizeId(Long shoesId, Long sizeId);
}
