package com.example.webdevuserservice.DTO;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserResponseDTO {
    @NonNull
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String email;
}
