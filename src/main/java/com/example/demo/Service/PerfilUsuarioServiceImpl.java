package com.example.demo.Service;

import com.example.demo.Model.PerfilUsuario;
import com.example.demo.Service.PerfilUsuarioService;
import com.example.demo.Repository.PerfilUsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilUsuarioServiceImpl implements PerfilUsuarioService {

    private final PerfilUsuarioRepository repository;

    public PerfilUsuarioServiceImpl(PerfilUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PerfilUsuario> getAll() {
        return repository.findAll();
    }
    // JPA: findAll()
    // Internamente SQL:
    // SELECT * FROM perfiles_usuario;

    @Override
    public PerfilUsuario save(PerfilUsuario perfil) {
        if (perfil == null) {
            throw new IllegalStateException("El perfil no puede ser nulo");
        }
        return repository.save(perfil);
    }
    // JPA: save(perfil)
    // Internamente SQL:
    // UPDATE perfiles_usuario SET documento = ?, telefono = ?, usuario_id = ? WHERE id = ?;

    @Override
    public Optional<PerfilUsuario> getById(Long id) {
        Optional<PerfilUsuario> perfil = repository.findById(id);
        if (perfil.isEmpty()) {
            throw new IllegalStateException("Perfil con id " + id + " no existe");
        }
        return perfil;
    }
    // JPA: save(perfil)
    // Internamente SQL:
    // UPDATE perfiles_usuario SET documento = ?, telefono = ?, usuario_id = ? WHERE id = ?;

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalStateException("Perfil con id " + id + " no existe");
        }
        repository.deleteById(id);
    }
    // JPA: deleteById(id)
    // Internamente SQL:
    // DELETE FROM perfiles_usuario WHERE id = ?;
}
