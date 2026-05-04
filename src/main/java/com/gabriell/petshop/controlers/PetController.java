package com.gabriell.petshop.controlers;

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

    @PostMapping(value = "/add/{id}")
    public ResponseEntity<String> addPet(@RequestBody Pet pet, @PathVariable Long id){
        Pet p = petService.addPet(pet, id);
        return ResponseEntity.ok().body("Pet adicionado com sucesso! id do pet: " + p.getId());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable Long id){
        return ResponseEntity.ok().body(petService.buscarPorId(id));
    }

    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<Pet> editPet(@PathVariable Long id, @RequestBody Pet pet){
        return ResponseEntity.ok().body(petService.editarPet(id, pet));
    }

}
