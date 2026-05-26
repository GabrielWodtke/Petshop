package com.gabriell.petshop.controlers;

import com.gabriell.petshop.dtos.PetDto;
import com.gabriell.petshop.dtos.RespostaPetDTO;
import com.gabriell.petshop.entities.Cliente;
import com.gabriell.petshop.entities.Pet;
import com.gabriell.petshop.services.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @PostMapping(value = "/{idDono}")
    public ResponseEntity<?> addPet(@RequestBody @Valid PetDto pet, @PathVariable Long idDono){
        Pet p = petService.addPet(pet, idDono);
        return ResponseEntity.status(201).body(new RespostaPetDTO(
                                                                    p.getId(),p.getNome(),
                                                                    p.getRaca(),
                                                                    p.getEspecie(),
                                                                    p.getDono().getNome() ,
                                                                    p.getDataNascimento()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPet(@PathVariable Long id){
        var pet = petService.buscarPorId(id);
        return ResponseEntity.ok(new RespostaPetDTO(pet.getId(), pet.getNome(), pet.getRaca(), pet.getEspecie(), pet.getDono().getNome(), pet.getDataNascimento()));
    }

    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<?> editPet(@PathVariable Long id, @RequestBody Pet obj){
        var pet = petService.editarPet(id, obj);
        return ResponseEntity.ok().body(new RespostaPetDTO(pet.getId(), pet.getNome(), pet.getRaca(), pet.getEspecie(), pet.getDono().getNome(), pet.getDataNascimento()));
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<String> deletePet(@PathVariable Long id){
        petService.removePet(id);
        return ResponseEntity.noContent().build();
    }

}
