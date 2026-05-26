package com.gabriell.petshop.controlers;

import com.gabriell.petshop.dtos.ClienteDTO;
import com.gabriell.petshop.dtos.ClienteRegistroDTO;
import com.gabriell.petshop.entities.Cliente;
import com.gabriell.petshop.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping(value = "/buscar/{id}")
    public ResponseEntity<?> getCliente(@PathVariable Long id){
        var cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok().body(new ClienteDTO(cliente.getNome(), cliente.getEmail(), cliente.getSenha(), cliente.getPetNomes(), cliente.getRole()));
    }

    @PostMapping(value = "/buscarEmail")
    public ResponseEntity<?> buscarEmail(@RequestBody String email){
        var cliente = clienteService.buscarPoremail(email);
        return ResponseEntity.status(201).body(new ClienteDTO(cliente.getNome(), cliente.getEmail(), cliente.getSenha(), cliente.getPetNomes(), cliente.getRole()));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody @Valid ClienteRegistroDTO obj){
        var cliente = clienteService.editarCliente(id, obj);
        return ResponseEntity.ok().body(new ClienteDTO(cliente.getNome(), cliente.getEmail(), cliente.getSenha(), cliente.getPetNomes(), cliente.getRole()));
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<Integer> removerCliente(@PathVariable Long id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
