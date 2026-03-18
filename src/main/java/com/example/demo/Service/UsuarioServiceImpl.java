package com.example.demo.Service;

import com.example.demo.Model.Usuario;
import com.example.demo.Service.UsuarioService;
import com.example.demo.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Usuario> getAll() {
        return repository.findAll();
    }

    @Override
    public Usuario save(Usuario usuario) {
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new IllegalStateException("El nombre no puede estar vacío");
        }
        return repository.save(usuario);
    }

    @Override
    public Optional<Usuario> getById(Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isEmpty()) {
            throw new IllegalStateException("Usuario con id " + id + " no existe");
        }
        return usuario;
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalStateException("Usuario con id " + id + " no existe");
        }
        repository.deleteById(id);
    }
}
