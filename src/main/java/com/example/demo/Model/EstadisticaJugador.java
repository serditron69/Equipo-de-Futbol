package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estadistica_jugador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadisticaJugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estadistica")
    private Integer idEstadistica;

    // Muchas estadísticas pertenecen a un jugador (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador", nullable = false)
    @JsonIgnoreProperties({"estadisticas", "equipo"})
    private Jugador jugador;

    // Muchas estadísticas pertenecen a un partido (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_partido", nullable = false)
    @JsonIgnoreProperties({"estadisticas", "equipoLocal", "equipoVisita"})
    private Partido partido;

    @Column(name = "minutos_jugados")
    private Integer minutosJugados;

    @Column(name = "goles")
    private Integer goles;

    @Column(name = "asistencias")
    private Integer asistencias;

    @Column(name = "tarjetas_amarillas")
    private Integer tarjetasAmarillas;

    @Column(name = "tarjetas_rojas")
    private Integer tarjetasRojas;
}