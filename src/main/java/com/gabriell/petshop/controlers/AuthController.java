package com.gabriell.petshop.controlers;

import com.gabriell.petshop.entities.Cliente;
import com.gabriell.petshop.security.JwtUtil;
import com.gabriell.petshop.services.ClienteService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    ClienteService service;
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Cliente usuario){
        if(service.buscarPoremail(usuario.getUsername()) != null){
            String token = JwtUtil.gerarToken(usuario);
            return Map.of("token", token);
        }
        return Map.of("Erro: ", "invalido");
    }
}
