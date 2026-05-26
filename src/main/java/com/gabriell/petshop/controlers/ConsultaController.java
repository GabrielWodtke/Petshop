package com.gabriell.petshop.controlers;

import com.gabriell.petshop.dtos.AgendamentoConsultaDto;
import com.gabriell.petshop.dtos.ConsultaDto;
import com.gabriell.petshop.entities.Consulta;
import com.gabriell.petshop.services.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    ConsultaService consultaService;

    @PostMapping("/agendar/{id}")
    public ResponseEntity<AgendamentoConsultaDto> agendarConsulta(@PathVariable Long id, @RequestBody @Valid ConsultaDto consulta){
        Consulta idConsulta = consultaService.addConsulta(id, consulta);
        return ResponseEntity.status(201).body(new AgendamentoConsultaDto
                (consulta.descricao(),
                        consulta.diagnostico(),
                        consulta.tratamento(),
                        consulta.data(),
                        idConsulta.getId()));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ConsultaDto> getConsulta(@PathVariable Long id){
        Consulta obj = consultaService.buscaPorId(id);
        ConsultaDto consulta = new ConsultaDto(obj.getDescricao(), obj.getDiagnostico(), obj.getTratamento(), obj.getData());
        return ResponseEntity.ok().body(consulta);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ConsultaDto> editarConsulta(@PathVariable Long id, @RequestBody @Valid ConsultaDto consulta){
        Consulta obj = consultaService.attConsulta(consulta, id);
        ConsultaDto retorno = new ConsultaDto(obj.getDescricao(), obj.getDiagnostico(), obj.getTratamento(), obj.getData());
        return ResponseEntity.ok().body(retorno);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerConsulta(@PathVariable Long id){
        consultaService.removeConsulta(id);
        return ResponseEntity.noContent().build();
    }
}
