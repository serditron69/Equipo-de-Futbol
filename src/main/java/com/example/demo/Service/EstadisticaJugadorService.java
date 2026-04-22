package com.example.demo.Service;

import com.example.demo.DTO.TotalGolesEquipoDTO;
import com.example.demo.Model.EstadisticaJugador;
import java.util.List;
import java.util.Optional;

public interface EstadisticaJugadorService {
    List<EstadisticaJugador> listarTodos();
    Optional<EstadisticaJugador> buscarPorId(Integer id);
    EstadisticaJugador guardar(EstadisticaJugador estadistica);
    EstadisticaJugador actualizar(Integer id, EstadisticaJugador estadistica);
    void eliminar(Integer id);
    TotalGolesEquipoDTO obtenerTotalGolesEquipo(Integer idEquipo);
}