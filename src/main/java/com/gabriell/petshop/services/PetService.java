package com.gabriell.petshop.services;


import com.gabriell.petshop.Exceptions.DadosInvalidosExceptions;
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
        return repository.save(pet);
    }

    public boolean removePet(Long id){
        Pet pet = buscarPorId(id);
        repository.delete(pet);
        return true;
    }

    public Pet buscarPorId(Long id){
        if(id > 0){
            return repository.findById(id).orElseThrow(() -> new RuntimeException("Pet não encontrado"));
        }
        else{
            throw new DadosInvalidosExceptions("Id menor que zero!");
        }
    }

    public boolean editarPet(Long id, Pet pet){
        Pet object = buscarPorId(id);

        object.setDono(pet.getDono());
        object.setNome(pet.getNome());
        object.setRaca(pet.getRaca());
        object.setDataNascimento(pet.getDataNascimento());
        object.setEspecie(pet.getEspecie());

        repository.save(object);
        return true;
    }


}
