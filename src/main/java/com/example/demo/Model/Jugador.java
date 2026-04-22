package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "jugador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jugador")
    private Integer idJugador;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "posicion", length = 50)
    private String posicion;

    @Column(name = "dorsal")
    private Integer dorsal;

    @Column(name = "fecha_nac")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNac;

    @Column(name = "nacionalidad", length = 100)
    private String nacionalidad;

    // Muchos jugadores pertenecen a un equipo (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipo", nullable = false)
    @JsonIgnoreProperties({"jugadores", "entrenadores", "partidosComoLocal", "partidosComoVisitante"})
    private Equipo equipo;

    // Un jugador tiene muchas estadísticas (1:N)
    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("jugador")
    private List<EstadisticaJugador> estadisticas;
}