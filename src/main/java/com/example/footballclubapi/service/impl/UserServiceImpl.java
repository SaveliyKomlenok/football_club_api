package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.dto.request.user.UserChangePasswordRequest;
import com.example.footballclubapi.entity.User;
import com.example.footballclubapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User changePassword(Long id, UserChangePasswordRequest request) {
        return null;
    }
}
