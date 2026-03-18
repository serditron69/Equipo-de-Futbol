package com.example.demo.Service;
import com.example.demo.Model.Estudiante;
import java.util.List;
import java.util.Optional;

public interface EstudianteService {

    List<Estudiante> getAll();
    Optional<Estudiante> getById(Long id);
    Estudiante save(Estudiante estudiante);
    void delete(Long id);
}
