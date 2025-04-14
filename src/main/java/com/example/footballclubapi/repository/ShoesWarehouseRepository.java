package com.example.footballclubapi.repository;

import com.example.footballclubapi.entity.ShoesWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoesWarehouseRepository extends JpaRepository<ShoesWarehouse, Long> {
}
