package com.example.footballclubapi.repository;

import com.example.footballclubapi.entity.ShoeSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeSizeRepository extends JpaRepository<ShoeSize, Long> {
}
