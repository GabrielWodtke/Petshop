package com.gabriell.petshop.dtos;

import com.gabriell.petshop.entities.Cliente;

import java.time.LocalDate;

public record PetDto(String nome, String raca, String especie, Cliente dono, LocalDate dataNascimento) {
}
