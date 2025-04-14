package com.example.footballclubapi.repository;

import com.example.footballclubapi.entity.ClothingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingTypeRepository extends JpaRepository<ClothingType, Long> {
}
