package com.gabriell.petshop.controlers;

import com.gabriell.petshop.dtos.ClienteLoginDTO;
import com.gabriell.petshop.dtos.ClienteRegistroDTO;
import com.gabriell.petshop.entities.Cliente;
import com.gabriell.petshop.security.JwtUtil;
import com.gabriell.petshop.services.ClienteService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ClienteService service;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid ClienteLoginDTO usuario){
        var obj = service.buscarPoremail(usuario.gmail());
        if(passwordEncoder.matches(usuario.senha(), obj.getSenha())){
            String token = JwtUtil.gerarToken(obj);
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: Email ou senha estão errados");
    }

    @PostMapping("/registro")
    public ResponseEntity<Cliente> registro(@RequestBody @Valid ClienteRegistroDTO cliente){
        return ResponseEntity.ok().body(service.addCliente(cliente));
    }
}
