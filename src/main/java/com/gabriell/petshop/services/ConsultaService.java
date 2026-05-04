package com.gabriell.petshop.services;

import com.gabriell.petshop.Exceptions.DadosInvalidosExceptions;
import com.gabriell.petshop.entities.Consulta;
import com.gabriell.petshop.entities.Pet;
import com.gabriell.petshop.repositorioes.ConsultaRepository;

import com.gabriell.petshop.repositorioes.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {
    @Autowired
    ConsultaRepository repository;

    @Autowired
    PetRepository petRepository;

    public Consulta addConsulta(Long petiD, Consulta consulta){
        Pet pet = petRepository.getReferenceById(petiD);

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
            return repository.findById(id).orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        }
        else{
            throw new DadosInvalidosExceptions("Id menor que zero!");
        }
    }

    public boolean attConsulta(Consulta consulta, Long id){
        Consulta object = buscaPorId(id);

        object.setPet(consulta.getPet());
        object.setData(consulta.getData());
        object.setDescricao(consulta.getDescricao());
        object.setTratamento(consulta.getTratamento());
        object.setDiagnostico(consulta.getDiagnostico());

        repository.save(object);
        return true;
    }
}
