package com.example.demo.Service;

import com.example.demo.Model.Categoria;
import com.example.demo.Service.CategoriaService;
import com.example.demo.Repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaServiceImpl(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Categoria> getAll() {
        return repository.findAll();
    }

    // JPA: findAll()
    // Internamente SQL:
    // SELECT * FROM categorias;


    @Override
    public Categoria save(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalStateException("La categoria no puede ser nula");
        }
        return repository.save(categoria);
    }
    // JPA: save(categoria)
    // Internamente SQL (POST - sin ID):
    // INSERT INTO categorias (name, description) VALUES (?, ?);
    // Internamente SQL (PATCH - con ID):
    // UPDATE categorias SET name = ?, description = ? WHERE id = ?;


    @Override
    public Optional<Categoria> getById(Long id) {
        Optional<Categoria> categoria = repository.findById(id);
        if (categoria.isEmpty()) {
            throw new IllegalStateException("Categoria con id " + id + " no existe");
        }
        return categoria;
    }
    // JPA: findById(id)
    // Internamente SQL:
    // SELECT * FROM categorias WHERE id = ?;


    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalStateException("Categoria con id " + id + " no existe");
        }
        repository.deleteById(id);
    }
    // JPA: deleteById(id)
    // Internamente SQL:
    // DELETE FROM categorias WHERE id = ?;

}
