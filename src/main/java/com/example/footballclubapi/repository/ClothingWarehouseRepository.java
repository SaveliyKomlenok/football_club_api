package com.example.footballclubapi.repository;

import com.example.footballclubapi.entity.ClothingWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingWarehouseRepository extends JpaRepository<ClothingWarehouse, Long> {
}
