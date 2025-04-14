package com.example.footballclubapi.repository;

import com.example.footballclubapi.entity.ClothSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClothSizeRepository extends JpaRepository<ClothSize, Long> {
    Optional<ClothSize> findBySize(String size);
}
