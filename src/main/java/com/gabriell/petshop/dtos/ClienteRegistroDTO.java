package com.gabriell.petshop.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClienteRegistroDTO (
        @NotBlank
        String nome,
        @Email
        String email,
        @NotNull
        Long telefone,
        @NotBlank(message = "A senha não pode ser vazia")
        @Size(min = 6, message = "A senha precisa ter no minímo 6 caracteres")
        String senha
        ){}
