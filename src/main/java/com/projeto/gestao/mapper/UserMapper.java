package com.projeto.gestao.mapper;

import com.projeto.gestao.dto.UserRequestDTO;
import com.projeto.gestao.dto.UserResponseDTO;
import com.projeto.gestao.entity.User;

public class UserMapper {

    public User toEntity(UserRequestDTO dto){
        return new User(dto.getName(), dto.getEmail(), dto.getPassword(), dto.getRole());
    }

    public UserResponseDTO toDto(User user){
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
