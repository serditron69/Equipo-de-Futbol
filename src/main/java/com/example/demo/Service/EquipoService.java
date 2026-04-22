package com.example.demo.Service;

import com.example.demo.Model.Equipo;
import java.util.List;
import java.util.Optional;

public interface EquipoService {
    List<Equipo> listarTodos();
    Optional<Equipo> buscarPorId(Integer id);
    Equipo guardar(Equipo equipo);
    Equipo actualizar(Integer id, Equipo equipo);
    void eliminar(Integer id);
}