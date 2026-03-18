package com.example.demo.Service;

import com.example.demo.Model.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<Categoria> getAll();
    Optional<Categoria> getById(Long id);
    Categoria save(Categoria categoria);
    void delete(Long id);
}
