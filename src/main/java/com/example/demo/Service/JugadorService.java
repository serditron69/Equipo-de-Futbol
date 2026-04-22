package com.example.demo.Service;

import com.example.demo.Model.Jugador;
import java.util.List;
import java.util.Optional;

public interface JugadorService {
    List<Jugador> listarTodos();
    Optional<Jugador> buscarPorId(Integer id);
    Jugador guardar(Jugador jugador);
    Jugador actualizar(Integer id, Jugador jugador);
    void eliminar(Integer id);
    List<Jugador> jugadoresPorEquipo(Integer idEquipo);
    List<Jugador> jugadoresConMasDeXGoles(Integer minGoles);
}