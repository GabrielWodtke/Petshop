package com.gabriell.petshop.controlers;

import com.gabriell.petshop.entities.Consulta;
import com.gabriell.petshop.services.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    ConsultaService consultaService;

    @PostMapping("/agendar/{id}")
    public ResponseEntity<String> agendarConsulta(@PathVariable Long id, @RequestBody Consulta consulta){
        Consulta c = consultaService.addConsulta(id, consulta);
        return ResponseEntity.status(201).body("Consulta agendada com sucesso! id: " + c.getId());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Consulta> getConsulta(@PathVariable Long id){
        return ResponseEntity.ok().body(consultaService.buscaPorId(id));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Consulta> editarConsulta(@PathVariable Long id, @RequestBody Consulta consulta){
        return ResponseEntity.ok().body(consultaService.attConsulta(consulta, id));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerConsulta(@PathVariable Long id){
        consultaService.removeConsulta(id);
        return ResponseEntity.noContent().build();
    }
}
