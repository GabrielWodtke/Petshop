package com.gabriell.petshop.security;

import com.gabriell.petshop.entities.Cliente;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = System.getenv("JWT_SECRET");

    static{
        if(SECRET == null ||  SECRET.isBlank()){
            throw new RuntimeException("JWT_SECRET esta vazio, configure-o antes de executar.");
        }
    }

    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(
                    SECRET
                            .getBytes()
            );

    public static String gerarToken(Cliente cliente) {

        return Jwts.builder()
                .subject(cliente.getUsername())
                .claim("role", cliente.getRole())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static String extrairUsername(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public static String extrairRole(String token) {

        Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("role", String.class);
    }

    public static boolean tokenValido(String token, String username) {

        String usernameToken = extrairUsername(token);

        return usernameToken.equals(username);
    }
}