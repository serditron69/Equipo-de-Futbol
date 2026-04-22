package com.example.demo.Repository;

import com.example.demo.Model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Integer> {

    // Consulta Nativa 1: Jugadores de un equipo específico
    @Query(value = """
            SELECT * FROM jugador
            WHERE id_equipo = :idEquipo
            """, nativeQuery = true)
    List<Jugador> findJugadoresByEquipo(@Param("idEquipo") Integer idEquipo);

    // Consulta Nativa 2: Jugadores con más de X goles
    @Query(value = """
            SELECT j.*
            FROM jugador j
            INNER JOIN estadistica_jugador e ON j.id_jugador = e.id_jugador
            GROUP BY j.id_jugador, j.nombre, j.posicion, j.dorsal,
                     j.fecha_nac, j.nacionalidad, j.id_equipo
            HAVING SUM(e.goles) > :minGoles
            ORDER BY SUM(e.goles) DESC
            """, nativeQuery = true)
    List<Jugador> findJugadoresConMasDeXGoles(@Param("minGoles") Integer minGoles);
}