package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.request.cartclothing.CartClothingRequest;
import com.example.footballclubapi.dto.response.cartclothing.CartClothingResponse;
import com.example.footballclubapi.dto.response.cartclothing.CartClothingListResponse;
import com.example.footballclubapi.entity.CartClothing;
import com.example.footballclubapi.entity.User;
import com.example.footballclubapi.mapper.CartClothingMapper;
import com.example.footballclubapi.service.CartClothingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart-clothing")
//@SecurityRequirement(name = "BearerAuth")
public class CartClothingController {
    private final CartClothingService cartClothingService;
    private final CartClothingMapper cartClothingMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CartClothingResponse> getById(@PathVariable Long id) {
        CartClothing cartClothing = cartClothingService.getById(id);
        return new ResponseEntity<>(cartClothingMapper.toResponse(cartClothing), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CartClothingListResponse> getAll(Principal principal) {
        List<CartClothing> cartClothingList = cartClothingService.getAll(1L); // getCurrentUser(principal).getId()
        return new ResponseEntity<>(cartClothingMapper.toListResponse(cartClothingList), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartClothingResponse> save(@RequestBody @Valid CartClothingRequest request, Principal principal) {
        CartClothing cartClothing = cartClothingService.save(cartClothingMapper.toEntity(request, 1L)); // getCurrentUser(principal).getId()
        return new ResponseEntity<>(cartClothingMapper.toResponse(cartClothing), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartClothingResponse> update(@PathVariable Long id, @RequestBody @Valid CartClothingRequest request, Principal principal) {
        CartClothing cartClothing = cartClothingService.update(cartClothingMapper.toEntity(id, request, 1L)); // getCurrentUser(principal).getId()
        return new ResponseEntity<>(cartClothingMapper.toResponse(cartClothing), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        cartClothingService.delete(id);
        return ResponseEntity.ok(String.format("Cart clothing with id %d removed", id));
    }

//    private User getCurrentUser(Principal principal) {
//        return (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
//    }
}