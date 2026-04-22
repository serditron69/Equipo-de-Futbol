package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "partido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partido")
    private Integer idPartido;

    @Column(name = "fecha")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @Column(name = "estadio", length = 100)
    private String estadio;

    // Muchos partidos tienen un equipo local (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_local", nullable = false)
    @JsonIgnoreProperties({"jugadores", "entrenadores", "partidosComoLocal", "partidosComoVisitante"})
    private Equipo equipoLocal;

    // Muchos partidos tienen un equipo visitante (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_visita", nullable = false)
    @JsonIgnoreProperties({"jugadores", "entrenadores", "partidosComoLocal", "partidosComoVisitante"})
    private Equipo equipoVisita;

    @Column(name = "goles_local")
    private Integer golesLocal;

    @Column(name = "goles_visita")
    private Integer golesVisita;

    // Un partido tiene muchas estadísticas de jugadores (1:N)
    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("partido")
    private List<EstadisticaJugador> estadisticas;
}