package com.projeto.gestao.dto;

import com.projeto.gestao.entity.UserRole;
import jakarta.validation.constraints.*;

public class UserRequestDTO {

    @NotBlank(message = "O Nome é obrigatorio")
    private String name;

    @Email(message = "Email invalido")
    @NotBlank(message = "Email é obrigatorio")
    private String email;

    @NotBlank(message = "Senha é obrigatoria")
    @Size(min = 6,message = "Senha deve ter no minimo 6 caracteres")
    private String password;

    @NotNull(message = "Role é obrigatorio")
    private UserRole role;
}
