package com.example.demo.Service;
import com.example.demo.Model.PerfilUsuario;
import java.util.List;
import java.util.Optional;

public interface PerfilUsuarioService {

    List<PerfilUsuario> getAll();
    Optional<PerfilUsuario> getById(Long id);
    PerfilUsuario save(PerfilUsuario perfil);
    void delete(Long id);
}
