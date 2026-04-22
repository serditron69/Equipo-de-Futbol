package com.example.demo.Service;

import com.example.demo.DTO.ResultadoPartidoDTO;
import com.example.demo.Model.Partido;
import java.util.List;
import java.util.Optional;

public interface PartidoService {
    List<Partido> listarTodos();
    Optional<Partido> buscarPorId(Integer id);
    Partido guardar(Partido partido);
    Partido actualizar(Integer id, Partido partido);
    void eliminar(Integer id);
    List<ResultadoPartidoDTO> obtenerResultadosPartidos();
}