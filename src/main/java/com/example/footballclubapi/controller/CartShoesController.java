package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.request.cartshoes.CartShoesRequest;
import com.example.footballclubapi.dto.response.cartshoes.CartShoesResponse;
import com.example.footballclubapi.dto.response.cartshoes.CartShoesListResponse;
import com.example.footballclubapi.entity.CartShoes;
import com.example.footballclubapi.entity.User;
import com.example.footballclubapi.mapper.CartShoesMapper;
import com.example.footballclubapi.service.CartShoesService;
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
@RequestMapping("/api/v1/cart-shoes")
//@SecurityRequirement(name = "BearerAuth")
public class CartShoesController {
    private final CartShoesService cartShoesService;
    private final CartShoesMapper cartShoesMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CartShoesResponse> getById(@PathVariable Long id) {
        CartShoes cartShoes = cartShoesService.getById(id);
        return new ResponseEntity<>(cartShoesMapper.toResponse(cartShoes), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CartShoesListResponse> getAll(Principal principal) {
        List<CartShoes> cartShoesList = cartShoesService.getAll(1L); // getCurrentUser(principal).getId()
        return new ResponseEntity<>(cartShoesMapper.toListResponse(cartShoesList), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartShoesResponse> save(@RequestBody @Valid CartShoesRequest request, Principal principal) {
        CartShoes cartShoes = cartShoesService.save(cartShoesMapper.toEntity(request, 1L)); // getCurrentUser(principal).getId()
        return new ResponseEntity<>(cartShoesMapper.toResponse(cartShoes), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartShoesResponse> update(@PathVariable Long id, @RequestBody @Valid CartShoesRequest request, Principal principal) {
        CartShoes cartShoes = cartShoesService.update(cartShoesMapper.toEntity(id, request, 1L)); // getCurrentUser(principal).getId()
        return new ResponseEntity<>(cartShoesMapper.toResponse(cartShoes), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        cartShoesService.delete(id);
        return ResponseEntity.ok(String.format("Cart shoes with id %d removed", id));
    }

//    private User getCurrentUser(Principal principal) {
//        return (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
//    }
}