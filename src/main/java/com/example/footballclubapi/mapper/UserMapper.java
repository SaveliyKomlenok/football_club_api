package com.example.footballclubapi.mapper;

import com.example.footballclubapi.dto.request.user.UserAuthenticateRequest;
import com.example.footballclubapi.dto.request.user.UserEditRequest;
import com.example.footballclubapi.dto.request.user.UserRegisterRequest;
import com.example.footballclubapi.dto.response.user.UserListResponse;
import com.example.footballclubapi.dto.response.user.UserResponse;
import com.example.footballclubapi.entity.User;
import com.example.footballclubapi.enumiration.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {
    //private final PasswordEncoder passwordEncoder;

    public User toEntity(UserRegisterRequest request) {
        return User.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .username(request.username())
                //.password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();
    }

    public User toEntity(UserAuthenticateRequest request) {
        return User.builder()
                .username(request.username())
                .password(request.password())
                .build();
    }

    public User toEntity(Long id, UserEditRequest request) {
        return User.builder()
                .id(id)
                .username(request.username())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .build();
    }

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .dateOfCreation(user.getDateOfCreation())
                .build();
    }

    public UserListResponse toListResponse(List<User> userList) {
        List<UserResponse> userResponseList = userList.stream()
                .map(this::toResponse)
                .toList();
        return UserListResponse.builder()
                .items(userResponseList)
                .build();
    }
}
