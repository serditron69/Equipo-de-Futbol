package com.example.demo.Service;

import com.example.demo.Model.Entrenador;
import java.util.List;
import java.util.Optional;

public interface EntrenadorService {
    List<Entrenador> listarTodos();
    Optional<Entrenador> buscarPorId(Integer id);
    Entrenador guardar(Entrenador entrenador);
    Entrenador actualizar(Integer id, Entrenador entrenador);
    void eliminar(Integer id);
}