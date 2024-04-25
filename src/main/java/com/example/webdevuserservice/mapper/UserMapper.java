package com.example.webdevuserservice.mapper;

import com.example.webdevuserservice.DTO.UserRegisterDTO;
import com.example.webdevuserservice.DTO.UserResponseDTO;
import com.example.webdevuserservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    UserResponseDTO toResponseDTO(User user);
    User fromResponseDTO(UserResponseDTO userResponseDTO);
    List<UserResponseDTO> toResponseDTOList(List<User> users);

    UserRegisterDTO toRegisterDTO(User user);
    User fromRegisterDTO(UserRegisterDTO userRegisterDTO);
}
