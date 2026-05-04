package com.gabriell.petshop.repositorioes;

import com.gabriell.petshop.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
