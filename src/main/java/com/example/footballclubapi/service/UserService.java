package com.example.footballclubapi.service;

import com.example.footballclubapi.dto.request.user.UserChangePasswordRequest;
import com.example.footballclubapi.entity.User;

import java.util.List;

public interface UserService {
    User getById(Long id);
    User getByUsername(String username);
    List<User> getAll();
    User save(User user);
    User update(User user);
    User changePassword(Long id, UserChangePasswordRequest request);
}
