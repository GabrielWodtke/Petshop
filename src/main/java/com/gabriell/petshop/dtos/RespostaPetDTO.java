package com.gabriell.petshop.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RespostaPetDTO(
        @NotNull
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        String raca,
        @NotBlank
        String especie,
        @NotBlank
        String dono,
        LocalDate dataNascimento

) {
}
