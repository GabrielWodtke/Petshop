package com.gabriell.petshop.services;

import com.gabriell.petshop.Exceptions.AutorizacaoException;
import com.gabriell.petshop.Exceptions.DadosInvalidosExceptions;
import com.gabriell.petshop.dtos.ConsultaDto;
import com.gabriell.petshop.entities.Consulta;
import com.gabriell.petshop.entities.Pet;
import com.gabriell.petshop.repositorioes.ClienteRepository;
import com.gabriell.petshop.repositorioes.ConsultaRepository;

import com.gabriell.petshop.repositorioes.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ConsultaService {
    @Autowired
    ConsultaRepository repository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    ClienteService clienteService;

    @Autowired
    PetService petService;

    public Consulta addConsulta(Long petiD, ConsultaDto obj){
        Pet pet = petService.buscarPorId(petiD);
        Consulta consulta = new Consulta(obj.descricao(), obj.diagnostico(), obj.tratamento(), obj.data());
        consulta.setPet(pet);
        return repository.save(consulta);
    }

    public boolean removeConsulta(Long id){
        Consulta object = buscaPorId(id);

        repository.delete(object);
        return true;
    }

    public Consulta buscaPorId(Long id){
        if(id > 0){
            var consulta = repository.findById(id).orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
            verificaoCliente(consulta.getPet().getDono().getId());
            return consulta;
        }
        else{
            throw new DadosInvalidosExceptions("Id menor que zero!");
        }
    }

    public Consulta attConsulta(ConsultaDto consulta, Long id){
        Consulta object = buscaPorId(id);

        object.setData(consulta.data());
        object.setDescricao(consulta.descricao());
        object.setTratamento(consulta.tratamento());
        object.setDiagnostico(consulta.diagnostico());

        return repository.save(object);
    }

    public boolean verificaoCliente(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        var obj = clienteService.buscarPoremail(email);
        var cliente = clienteRepository.findById(id).orElseThrow(() -> new DadosInvalidosExceptions("Cliente não encontrado"));

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
