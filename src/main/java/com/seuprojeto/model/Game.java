package com.seuprojeto.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "games" )
@Data
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome das equipes e data/hora da partida
    private String homeTeam;
    private String awayTeam;
    private LocalDateTime gameDate;

    // Estado da partida: Scheduled, Live, Finished
    private String status;

    // Resultado (opcional)
    private String result;
}

