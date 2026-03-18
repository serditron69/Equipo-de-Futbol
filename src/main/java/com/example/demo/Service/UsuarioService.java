package com.example.demo.Service;

import com.example.demo.Model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> getAll();
    Usuario save(Usuario usuario);
    Optional<Usuario> getById(Long id);
    void delete(Long id);
}
