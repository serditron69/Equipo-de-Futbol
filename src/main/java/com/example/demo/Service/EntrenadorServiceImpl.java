package com.example.demo.Service;

import com.example.demo.Model.Entrenador;
import com.example.demo.Repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorServiceImpl implements EntrenadorService {

    private final EntrenadorRepository repository;

    public EntrenadorServiceImpl(EntrenadorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Entrenador> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Entrenador> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Entrenador guardar(Entrenador entrenador) {
        return repository.save(entrenador);
    }

    @Override
    public Entrenador actualizar(Integer id, Entrenador entrenador) {
        entrenador.setIdEntrenador(id);
        return repository.save(entrenador);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}