package com.gabriell.petshop.services;


import com.gabriell.petshop.entities.Cliente;
import com.gabriell.petshop.entities.Pet;
import com.gabriell.petshop.repositorioes.ClienteRepository;
import com.gabriell.petshop.repositorioes.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    @Autowired
    PetRepository repository;

    @Autowired
    ClienteRepository repositoryCliente;

    public Pet addPet(Pet pet, Long clienteId){
        Cliente cliente = repositoryCliente.getReferenceById(clienteId);
        pet.setDono(cliente);
        return objeto = repository.save(pet);

    }
}
