package com.gabriell.petshop.services;

import com.gabriell.petshop.Exceptions.DadosInvalidosExceptions;
import com.gabriell.petshop.entities.Cliente;
import com.gabriell.petshop.repositorioes.ClienteRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService implements UserDetailsService {
    @Autowired
    ClienteRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Cliente addCliente(Cliente cliente){
        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
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

    public Cliente editarCliente(Long id, @NotNull Cliente c){
        Cliente object = buscarPorId(id);

        object.setEmail(c.getEmail());
        object.setNome(c.getNome());
        object.setSenha(passwordEncoder.encode(c.getSenha()));
        object.setTelefone(c.getTelefone());

        return repository.save(object);

    }

    public Cliente buscarPoremail(String email){
       return repository.findByemail(email).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buscarPoremail(username);
    }
}
