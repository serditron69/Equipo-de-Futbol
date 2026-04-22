package com.example.demo.Service;

import com.example.demo.Model.Equipo;
import com.example.demo.Repository.EquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository repository;

    public EquipoServiceImpl(EquipoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Equipo> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Equipo> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Equipo guardar(Equipo equipo) {
        return repository.save(equipo);
    }

    @Override
    public Equipo actualizar(Integer id, Equipo equipo) {
        equipo.setIdEquipo(id);
        return repository.save(equipo);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}