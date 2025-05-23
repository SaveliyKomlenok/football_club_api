package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.response.cart.CartResponse;
import com.example.footballclubapi.entity.User;
import com.example.footballclubapi.service.CartService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.example.footballclubapi.util.Constants.CROSS_LOCALHOST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
@SecurityRequirement(name = "BearerAuth")
@CrossOrigin(origins = CROSS_LOCALHOST)
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartResponse> listOfCarts(Principal principal) {
        return new ResponseEntity<>(cartService.getAll(getCurrentUser(principal).getId()), HttpStatus.OK);
    }

    private User getCurrentUser(Principal principal) {
        return (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }
}
