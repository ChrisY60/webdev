package com.example.webdevuserservice.service;

import com.example.webdevuserservice.DTO.UserRegisterDTO;
import com.example.webdevuserservice.DTO.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRegisterDTO userRegisterDTO);
    UserResponseDTO getUser(Long id);
    UserResponseDTO getUserByUsername(String username);
    UserResponseDTO getUserByEmail(String email);
    List<UserResponseDTO> getAllSubscribers(Long id);
    void subscribe(Long id, Long subscriptionId);
    void unsubscribe(Long id, Long subscriptionId);
}
