package com.example.demo.Repository;

import com.example.demo.DTO.TotalGolesEquipoDTO;
import com.example.demo.Model.EstadisticaJugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadisticaJugadorRepository extends JpaRepository<EstadisticaJugador, Integer> {

    // Consulta Nativa 3: Total de goles de un equipo en todos sus partidos
    @Query(value = """
            SELECT
                eq.nombre AS nombreEquipo,
                COALESCE(SUM(
                    CASE
                        WHEN p.equipo_local  = :idEquipo THEN p.goles_local
                        WHEN p.equipo_visita = :idEquipo THEN p.goles_visita
                        ELSE 0
                    END
                ), 0) AS totalGoles
            FROM equipo eq
            LEFT JOIN partido p
                ON eq.id_equipo = p.equipo_local
                OR eq.id_equipo = p.equipo_visita
            WHERE eq.id_equipo = :idEquipo
            GROUP BY eq.nombre
            """, nativeQuery = true)
    TotalGolesEquipoDTO obtenerTotalGolesEquipo(@Param("idEquipo") Integer idEquipo);
}