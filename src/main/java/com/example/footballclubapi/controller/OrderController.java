package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.request.order.OrderRequest;
import com.example.footballclubapi.dto.response.order.OrderListResponse;
import com.example.footballclubapi.dto.response.order.OrderResponse;
import com.example.footballclubapi.entity.Order;
import com.example.footballclubapi.entity.User;
import com.example.footballclubapi.mapper.OrderMapper;
import com.example.footballclubapi.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
//@SecurityRequirement(name = "BearerAuth")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<OrderListResponse> findAll(Principal principal) {
        List<Order> orderList = orderService.getAll(1L); // getCurrentUser(principal).getId()
        return new ResponseEntity<>(orderMapper.toListResponse(orderList), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest request, Principal principal) {
        Order order = orderService.save(orderMapper.toEntity(request, 1L)); // getCurrentUser(principal).getId()
        return new ResponseEntity<>(orderMapper.toResponse(order), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        orderService.delete(id);
        return ResponseEntity.ok(String.format("Order with id %d removed", id));
    }

//    private User getCurrentUser(Principal principal) {
//        return (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
//    }
}
