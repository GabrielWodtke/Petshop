package com.gabriell.petshop.controlers;

import com.gabriell.petshop.entities.Cliente;
import com.gabriell.petshop.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping(value = "/buscar/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id){
        return ResponseEntity.ok().body(clienteService.buscarPorId(id));
    }

    @PostMapping(value = "/buscarEmail")
    public ResponseEntity<Cliente> buscarEmail(@RequestBody String email){
        return ResponseEntity.status(201).body(clienteService.buscarPoremail(email));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> addCliente(@RequestBody Cliente cliente){
        Cliente c = clienteService.addCliente(cliente);
        return ResponseEntity.status(201).body("Cliente adicionado com sucesso! id do cliente: " + c.getId());
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        return ResponseEntity.ok().body(clienteService.editarCliente(id, cliente));
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<Integer> removerCliente(@PathVariable Long id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
