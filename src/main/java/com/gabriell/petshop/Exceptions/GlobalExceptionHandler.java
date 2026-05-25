package com.gabriell.petshop.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DadosInvalidosExceptions.class)
    public ResponseEntity<Map<String, Object>> handleDadosInvalidosException(DadosInvalidosExceptions e){
        Map<String, Object> response = new HashMap<>();
        response.put("Status", 400);
        response.put("Mensagem", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.badRequest().body(response);
      }


    @ExceptionHandler(AutenticacaoException.class)
    public ResponseEntity<Map<String, Object>> handleAutenticacaoException(AutenticacaoException e){
        Map<String, Object> response = new HashMap<>();
        response.put("Status", 401);
        response.put("Mensagem", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(401).body(response);
    }

    @ExceptionHandler(AutorizacaoException.class)
    public ResponseEntity<Map<String, Object>> handleAutorizacaoExpection(AutorizacaoException e){
        Map<String, Object> response = new HashMap<>();
        response.put("Status", 403);
        response.put("Mensagem", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(403).body(response);
    }

    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException e){
        Map<String, Object> response = new HashMap<>();
        response.put("Status", 404);
        response.put("Mensagem", e.getMessage());
        response.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(404).body(response);
    }


}
