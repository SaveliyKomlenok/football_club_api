package com.example.footballclubapi.repository;

import com.example.footballclubapi.entity.ShoesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoesTypeRepository extends JpaRepository<ShoesType, Long> {
}
