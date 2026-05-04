package com.gabriell.petshop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;
    private String descricao;
    private String diagnostico;
    private String tratamento;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
