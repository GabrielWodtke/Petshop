package com.gabriell.petshop.services;

import com.gabriell.petshop.Exceptions.AutorizacaoException;
import com.gabriell.petshop.Exceptions.DadosInvalidosExceptions;
import com.gabriell.petshop.entities.Cliente;
import com.gabriell.petshop.repositorioes.ClienteRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService implements UserDetailsService {
    @Autowired
    ClienteRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Cliente addCliente(Cliente cliente) {
        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
        return repository.save(cliente);
    }

    public void delete(Long id) {
        Cliente cliente = buscarPorId(id);
        repository.delete(cliente);
    }

    public boolean verificaoCliente(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        var obj = buscarPoremail(email);
        var cliente = repository.findById(id).orElseThrow(() -> new DadosInvalidosExceptions("Cliente não encontrado"));

        if (auth.getAuthorities().stream().
                anyMatch(a -> a.getAuthority().equals("ROLE_CLIENT"))) {
            if (!Objects.equals(obj.getId(), cliente.getId())) {
                throw new AutorizacaoException("Voce não possui autorização para fazer essa ação!");
            } else {
                return true;
            }
        } else return auth.getAuthorities().stream().
                anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }


    public Cliente buscarPorId(Long id) {
        if (id > 0 && verificaoCliente(id)) {
            return repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        } else {
            throw new DadosInvalidosExceptions("Dados invalidos");
        }
    }

    public Cliente editarCliente(Long id, @NotNull Cliente c) {
        verificaoCliente(id);
        Cliente object = buscarPorId(id);

        object.setEmail(c.getEmail());
        object.setNome(c.getNome());
        object.setSenha(passwordEncoder.encode(c.getSenha()));
        object.setTelefone(c.getTelefone());

        return repository.save(object);
    }

    public Cliente buscarPoremail(String email) {
        return repository.findByemail(email).orElseThrow(() -> new DadosInvalidosExceptions("Cliente não encontrado"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buscarPoremail(username);
    }
}
