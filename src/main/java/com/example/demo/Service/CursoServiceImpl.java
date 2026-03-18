package com.example.demo.Service;

import com.example.demo.Model.Curso;
import com.example.demo.Service.CursoService;
import com.example.demo.Repository.CursoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository repository;

    public CursoServiceImpl(CursoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Curso> getAll() {
        return repository.findAll();
    }
    // JPA: findAll()
    // Internamente SQL:
    // SELECT * FROM cursos;


    @Override
    public Curso save(Curso curso) {
        if (curso == null) {
            throw new IllegalStateException("El curso no puede ser nulo");
        }
        return repository.save(curso);
    }
    // JPA: save(curso)
    // Internamente SQL:
    // INSERT INTO cursos (nombre, creditos) VALUES (?, ?);


    @Override
    public Optional<Curso> getById(Long id) {
        Optional<Curso> curso = repository.findById(id);
        if (curso.isEmpty()) {
            throw new IllegalStateException("Curso con id " + id + " no existe");
        }
        return curso;
    }
    // JPA: findById(id)
    // Internamente SQL:
    // SELECT * FROM cursos WHERE id = ?;


    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalStateException("Curso con id " + id + " no existe");
        }
        repository.deleteById(id);
    }
    // JPA: deleteById(id)
    // Internamente SQL:
    // DELETE FROM cursos WHERE id = ?;

}
