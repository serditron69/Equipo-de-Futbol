package com.example.demo.Service;

import com.example.demo.DTO.TotalGolesEquipoDTO;
import com.example.demo.Model.EstadisticaJugador;
import com.example.demo.Repository.EstadisticaJugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadisticaJugadorServiceImpl implements EstadisticaJugadorService {

    private final EstadisticaJugadorRepository repository;

    public EstadisticaJugadorServiceImpl(EstadisticaJugadorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EstadisticaJugador> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<EstadisticaJugador> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public EstadisticaJugador guardar(EstadisticaJugador estadistica) {
        return repository.save(estadistica);
    }

    @Override
    public EstadisticaJugador actualizar(Integer id, EstadisticaJugador estadistica) {
        estadistica.setIdEstadistica(id);
        return repository.save(estadistica);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public TotalGolesEquipoDTO obtenerTotalGolesEquipo(Integer idEquipo) {
        return repository.obtenerTotalGolesEquipo(idEquipo);
    }
}