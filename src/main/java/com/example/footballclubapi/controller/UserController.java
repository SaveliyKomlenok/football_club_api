package com.example.footballclubapi.controller;

import com.example.footballclubapi.dto.request.user.UserChangePasswordRequest;
import com.example.footballclubapi.dto.request.user.UserEditRequest;
import com.example.footballclubapi.dto.response.user.UserListResponse;
import com.example.footballclubapi.dto.response.user.UserResponse;
import com.example.footballclubapi.entity.User;
import com.example.footballclubapi.mapper.UserMapper;
import com.example.footballclubapi.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "BearerAuth")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserListResponse> findAll() {
        List<User> userList = userService.getAll();
        return new ResponseEntity<>(userMapper.toListResponse(userList), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponse> findById(Principal principal) {
        User user = userService.getById(getCurrentUser(principal).getId());
        return new ResponseEntity<>(userMapper.toResponse(user), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> update(@RequestBody UserEditRequest request, Principal principal) {
        User user = userService.update(userMapper.toEntity(getCurrentUser(principal).getId(), request));
        return new ResponseEntity<>(userMapper.toResponse(user), HttpStatus.OK);
    }

    @PutMapping("/change-password")
    public ResponseEntity<UserResponse> changePassword(@RequestBody UserChangePasswordRequest request, Principal principal) {
        User user = userService.changePassword(getCurrentUser(principal).getId(), request);
        return new ResponseEntity<>(userMapper.toResponse(user), HttpStatus.OK);
    }

    private User getCurrentUser(Principal principal) {
        return (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }
}
