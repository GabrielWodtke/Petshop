package com.gabriell.petshop.dtos;

import com.gabriell.petshop.entities.Pet;

import java.util.List;

public record ClienteDTO(String nome, String email, String senha, List<String> pets, String role) {
}
