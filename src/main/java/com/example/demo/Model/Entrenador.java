package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entrenador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrenador")
    private Integer idEntrenador;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "especialidad", length = 100)
    private String especialidad;

    // Muchos entrenadores pertenecen a un equipo (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo", nullable = false)
    @JsonIgnoreProperties({"jugadores", "entrenadores", "partidosComoLocal", "partidosComoVisitante"})
    private Equipo equipo;
}