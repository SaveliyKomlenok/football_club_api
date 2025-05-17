package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.request.user.UserAuthenticateRequest;
import com.example.footballclubapi.dto.request.user.UserRegisterRequest;
import com.example.footballclubapi.dto.response.auth.AuthenticationResponse;
import com.example.footballclubapi.mapper.UserMapper;
import com.example.footballclubapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.footballclubapi.util.Constants.CROSS_LOCALHOST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = CROSS_LOCALHOST)
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserRegisterRequest request) {
        return new ResponseEntity<>(authenticationService.register(userMapper.toEntity(request)), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody UserAuthenticateRequest request) {
        return new ResponseEntity<>(authenticationService.authenticate(userMapper.toEntity(request)), HttpStatus.OK);
    }
}