package com.example.footballclubapi.repository;

import com.example.footballclubapi.entity.OrderShoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderShoesRepository extends JpaRepository<OrderShoes, Long> {
    List<OrderShoes> findOrderShoesByOrderId(Long orderId);

    void deleteOrderShoesByOrderId(Long orderId);
}
