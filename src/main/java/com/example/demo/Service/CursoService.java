package com.example.demo.Service;
import com.example.demo.Model.Curso;
import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> getAll();
    Optional<Curso> getById(Long id);
    Curso save(Curso curso);
    void delete(Long id);
}
