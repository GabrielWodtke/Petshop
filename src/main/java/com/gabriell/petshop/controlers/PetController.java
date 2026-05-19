package com.gabriell.petshop.controlers;

import com.gabriell.petshop.dtos.PetDto;
import com.gabriell.petshop.entities.Cliente;
import com.gabriell.petshop.entities.Pet;
import com.gabriell.petshop.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @PostMapping(value = "/{idDono}")
    public ResponseEntity<?> addPet(@RequestBody Pet pet, @PathVariable Long idDono){
        Pet p = petService.addPet(pet, idDono);
        return ResponseEntity.status(201).body(new PetDto(pet.getNome(), pet.getRaca(), pet.getEspecie(), pet.getDono(), pet.getDataNascimento()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable Long id){
        return ResponseEntity.ok().body(petService.buscarPorId(id));
    }

    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<Pet> editPet(@PathVariable Long id, @RequestBody Pet pet){
        return ResponseEntity.ok().body(petService.editarPet(id, pet));
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<String> deletePet(@PathVariable Long id){
        petService.removePet(id);
        return ResponseEntity.noContent().build();
    }

}
