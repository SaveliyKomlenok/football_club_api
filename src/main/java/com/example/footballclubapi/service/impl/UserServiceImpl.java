package com.example.footballclubapi.service.impl;

import com.example.footballclubapi.dto.request.user.UserChangePasswordRequest;
import com.example.footballclubapi.entity.User;
import com.example.footballclubapi.exception.UserNotExistsException;
import com.example.footballclubapi.repository.UserRepository;
import com.example.footballclubapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.footballclubapi.util.ExceptionMessages.USER_NOT_EXISTS;
import static com.example.footballclubapi.util.ExceptionMessages.USER_WITH_USERNAME_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotExistsException(String.format(USER_WITH_USERNAME_NOT_EXISTS, username)));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserNotExistsException(String.format(USER_WITH_USERNAME_NOT_EXISTS, user.getUsername()));
        }
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User updatedUser = getOrThrow(user.getId());
        updatedUser.setFirstname(user.getFirstname());
        updatedUser.setLastname(user.getLastname());
        updatedUser.setUsername(user.getUsername());
        return userRepository.save(updatedUser);
    }

    @Override
    public User changePassword(Long id, UserChangePasswordRequest request) {
        User user = getOrThrow(id);
        user.setPassword(passwordEncoder.encode(request.password()));
        return userRepository.save(user);
    }

    private User getOrThrow(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotExistsException(String.format(USER_NOT_EXISTS, id)));
    }
}
