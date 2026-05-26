package com.gabriell.petshop.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record ClienteLoginDTO(
        @Email
        String gmail,
        @Size(min = 6, message = "A senha precisa ter no minímo 6 caracteres")
        String senha
) {
}
