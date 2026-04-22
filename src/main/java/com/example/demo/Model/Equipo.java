package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "equipo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private Integer idEquipo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "ciudad", length = 100)
    private String ciudad;

    @Column(name = "fundacion")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fundacion;

    // Un equipo tiene muchos jugadores (1:N)
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("equipo")
    private List<Jugador> jugadores;

    // Un equipo tiene muchos entrenadores (1:N)
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("equipo")
    private List<Entrenador> entrenadores;

    // Un equipo puede jugar muchos partidos como local (1:N)
    @OneToMany(mappedBy = "equipoLocal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"equipoLocal", "equipoVisita"})
    private List<Partido> partidosComoLocal;

    // Un equipo puede jugar muchos partidos como visitante (1:N)
    @OneToMany(mappedBy = "equipoVisita", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"equipoLocal", "equipoVisita"})
    private List<Partido> partidosComoVisitante;
}