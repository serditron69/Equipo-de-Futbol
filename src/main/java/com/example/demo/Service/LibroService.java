package com.example.demo.Service;
import com.example.demo.Model.Libro;
import java.util.List;
import java.util.Optional;

public interface LibroService {

    List<Libro> getAll();
    Optional<Libro> getById(Long id);
    Libro save(Libro libro);
    void delete(Long id);
}
