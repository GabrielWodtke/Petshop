package com.gabriell.petshop.services;

import com.gabriell.petshop.Exceptions.DadosInvalidosExceptions;
import com.gabriell.petshop.entities.Cliente;
import com.gabriell.petshop.repositorioes.ClienteRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository repository;

    public Cliente addCliente(Cliente cliente){
    return repository.save(cliente);
    }

    public void delete(Long id){
        Cliente cliente = repository.getReferenceById(id);
        repository.delete(cliente);
    }

    public Cliente buscarPorId(Long id){
        if(id > 0){
            return repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        }
        else{
            throw new DadosInvalidosExceptions("Id menor que zero!");
        }
    }

    public boolean editarCliente(Long id, @NotNull Cliente c){
        Cliente object = buscarPorId(id);

        object.setEmail(c.getEmail());
        object.setNome(c.getNome());
        object.setSenha(c.getSenha());
        object.setTelefone(c.getTelefone());

        repository.save(object);
        return true;
    }

    public Cliente buscarPoremail(String email){
       return repository.findByemail(email).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }
}
