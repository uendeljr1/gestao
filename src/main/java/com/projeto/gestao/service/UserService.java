package com.projeto.gestao.service;

import com.projeto.gestao.dto.UserRequestDTO;
import com.projeto.gestao.dto.UserResponseDTO;
import com.projeto.gestao.entity.User;
import com.projeto.gestao.exception.BusinessException;
import com.projeto.gestao.exception.ResourceNotFoundException;
import com.projeto.gestao.mapper.UserMapper;
import com.projeto.gestao.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponseDTO create(UserRequestDTO dto){
        if (repository.existsByEmail(dto.getEmail())){
            throw new BusinessException("Email já cadastrado");}

        User user = UserMapper.toEntity(dto);

        return UserMapper.toDto(repository.save(user));
    }

    public UserResponseDTO findById(Long id){
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado com id: " + id));

        return UserMapper.toDto(user);
    }

    public List<UserResponseDTO> findAll(){

        return repository.findAll().stream().map(UserMapper::toDto).toList();
    }

    public UserResponseDTO update(Long id,UserRequestDTO dto){
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado com id: " + id));

        if (!user.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail())){
            throw new BusinessException("Email ja cadastrado");
        }
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        return UserMapper.toDto(repository.save(user));
    }

    public void deleteById(Long id){
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado com id: " + id));

        repository.delete(user);
    }
}
