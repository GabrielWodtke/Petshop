package com.gabriell.petshop.dtos;

import com.gabriell.petshop.entities.Cliente;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record PetDto(
        @NotBlank
        String nome,
        @NotBlank
        String raca,
        @NotBlank
        String especie,
        LocalDate dataNascimento) {

}
