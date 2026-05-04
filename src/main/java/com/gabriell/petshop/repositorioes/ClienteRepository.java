package com.gabriell.petshop.repositorioes;

import com.gabriell.petshop.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByemail(String email);
}
