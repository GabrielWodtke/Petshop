package com.gabriell.petshop.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoConsultaDto(@NotBlank(message = "A descrição não pode ser vazia")
                                     String descricao,
                                     @NotBlank(message = "O diagnóstico não pode ser vazio")
                                     String diagnostico,
                                     @NotBlank(message = "O tratamento não pode ser vazio")
                                     String tratamento,
                                     @NotNull
                                     LocalDateTime data,
                                     @NotNull
                                     Long id
                                     ) {
}
