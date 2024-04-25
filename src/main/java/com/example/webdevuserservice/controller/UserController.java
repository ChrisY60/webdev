package com.example.webdevuserservice.controller;

import com.example.webdevuserservice.DTO.UserRegisterDTO;
import com.example.webdevuserservice.DTO.UserResponseDTO;
import com.example.webdevuserservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(
            @PathVariable String username
    ) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(
            @PathVariable String email
    ) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createUser(
            @RequestBody UserRegisterDTO userRegisterDTO
    ) {
        return ResponseEntity.ok(userService.createUser(userRegisterDTO));
    }

    @GetMapping("/subscribers/{id}")
    public ResponseEntity<List<UserResponseDTO>> getAllSubscribers(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(userService.getAllSubscribers(id));
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Void> subscribe(
            @RequestParam Long id,
            @RequestParam Long subscriptionId
    ) {
        userService.subscribe(id, subscriptionId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<Void> unsubscribe(
            @RequestParam Long id,
            @RequestParam Long subscriptionId
    ) {
        userService.unsubscribe(id, subscriptionId);
        return ResponseEntity.ok().build();
    }
}
