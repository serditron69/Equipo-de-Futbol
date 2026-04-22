package com.example.demo.Repository;

import com.example.demo.DTO.ResultadoPartidoDTO;
import com.example.demo.Model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {

    // Consulta Nativa 4: Resultados de partidos con nombres de equipos
    @Query(value = """
            SELECT
                p.fecha           AS fecha,
                el.nombre         AS equipoLocal,
                ev.nombre         AS equipoVisitante,
                p.goles_local     AS golesLocal,
                p.goles_visita    AS golesVisita
            FROM partido p
            INNER JOIN equipo el ON p.equipo_local  = el.id_equipo
            INNER JOIN equipo ev ON p.equipo_visita = ev.id_equipo
            ORDER BY p.fecha DESC
            """, nativeQuery = true)
    List<ResultadoPartidoDTO> obtenerResultadosPartidos();
}