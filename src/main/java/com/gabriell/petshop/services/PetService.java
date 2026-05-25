package com.gabriell.petshop.services;


import com.gabriell.petshop.Exceptions.AutorizacaoException;
import com.gabriell.petshop.Exceptions.DadosInvalidosExceptions;
import com.gabriell.petshop.entities.Cliente;
import com.gabriell.petshop.entities.Pet;
import com.gabriell.petshop.repositorioes.ClienteRepository;
import com.gabriell.petshop.repositorioes.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PetService {
    @Autowired
    PetRepository repository;

    @Autowired
    ClienteRepository repositoryCliente;

    @Autowired
    ClienteService clienteService;

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
            var pet = repository.findById(id).orElseThrow(() -> new RuntimeException("Pet não encontrado"));
            if(verificaoCliente(pet.getDono().getId())){
                return pet;
            }
        }
        else{
            throw new DadosInvalidosExceptions("Id menor que zero!");
        }
        return null;
    }

    public Pet editarPet(Long id, Pet pet){
        Pet object = buscarPorId(id);
        verificaoCliente(object.getDono().getId());
        object.setNome(pet.getNome());
        object.setRaca(pet.getRaca());
        object.setDataNascimento(pet.getDataNascimento());
        object.setEspecie(pet.getEspecie());

        return repository.save(object);
    }

    public boolean verificaoCliente(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        var obj = clienteService.buscarPoremail(email);
        var cliente = repositoryCliente.findById(id).orElseThrow(() -> new DadosInvalidosExceptions("Cliente não encontrado"));

        if (auth.getAuthorities().stream().
                anyMatch(a -> a.getAuthority().equals("ROLE_CLIENT"))) {
            if (!Objects.equals(obj.getId(), cliente.getId())) {
                throw new AutorizacaoException("Você apenas pode alterar e pesquisar dados do seu pet!");
            } else {
                return true;
            }
        } else return auth.getAuthorities().stream().
                anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }


}
