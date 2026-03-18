package com.example.demo.Service;

import com.example.demo.Model.Estudiante;
import com.example.demo.Service.EstudianteService;
import com.example.demo.Repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository repository;

    public EstudianteServiceImpl(EstudianteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Estudiante> getAll() {
        return repository.findAll();
    }
    // JPA: findAll()
    // Internamente SQL:
    // SELECT * FROM estudiantes;


    @Override
    public Estudiante save(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalStateException("El estudiante no puede ser nulo");
        }
        return repository.save(estudiante);
    }
    // JPA: save(estudiante)
    // Internamente SQL (Hibernate ejecuta DOS inserts automáticamente):
    // INSERT INTO estudiantes (nombre, correo) VALUES (?, ?);
    // INSERT INTO estudiante_curso (estudiante_id, curso_id) VALUES (?, ?);


    @Override
    public Optional<Estudiante> getById(Long id) {
        Optional<Estudiante> estudiante = repository.findById(id);
        if (estudiante.isEmpty()) {
            throw new IllegalStateException("Estudiante con id " + id + " no existe");
        }
        return estudiante;
    }
    // JPA: findById(id)
    // Internamente SQL:
    // SELECT * FROM estudiantes WHERE id = ?;


    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalStateException("Estudiante con id " + id + " no existe");
        }
        repository.deleteById(id);
    }
    // JPA: deleteById(id)
    // Internamente SQL:
    // DELETE FROM estudiantes WHERE id = ?;

}
