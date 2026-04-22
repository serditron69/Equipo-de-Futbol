package com.example.demo.Service;

import com.example.demo.DTO.ResultadoPartidoDTO;
import com.example.demo.Model.Partido;
import com.example.demo.Repository.PartidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartidoServiceImpl implements PartidoService {

    private final PartidoRepository repository;

    public PartidoServiceImpl(PartidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Partido> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Partido> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Partido guardar(Partido partido) {
        return repository.save(partido);
    }

    @Override
    public Partido actualizar(Integer id, Partido partido) {
        partido.setIdPartido(id);
        return repository.save(partido);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<ResultadoPartidoDTO> obtenerResultadosPartidos() {
        return repository.obtenerResultadosPartidos();
    }
}