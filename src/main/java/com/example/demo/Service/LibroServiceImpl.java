package com.example.demo.Service;

import com.example.demo.Model.Libro;
import com.example.demo.Service.LibroService;
import com.example.demo.Repository.LibroRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository repository;

    public LibroServiceImpl(LibroRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Libro> getAll() {
        return repository.findAll();
    }
    // JPA: findAll()
    // Internamente SQL:
    // SELECT * FROM libros;


    @Override
    public Libro save(Libro libro) {
        if (libro == null) {
            throw new IllegalStateException("El libro no puede ser nulo");
        }
        return repository.save(libro);
    }
    // JPA: save(libro)
    // Internamente SQL:
    // INSERT INTO libros (title, author, anio_publicacion, categoria_id)
    // VALUES (?, ?, ?, ?);


    @Override
    public Optional<Libro> getById(Long id) {
        Optional<Libro> libro = repository.findById(id);
        if (libro.isEmpty()) {
            throw new IllegalStateException("Libro con id " + id + " no existe");
        }
        return libro;
    }
    // JPA: findById(id)
    // Internamente SQL:
    // SELECT * FROM libros WHERE id = ?;


    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalStateException("Libro con id " + id + " no existe");
        }
        repository.deleteById(id);
    }
    // JPA: deleteById(id)
    // Internamente SQL:
    // DELETE FROM libros WHERE id = ?;

}
