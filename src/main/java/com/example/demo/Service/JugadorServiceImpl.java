package com.example.demo.Service;

import com.example.demo.Model.Jugador;
import com.example.demo.Repository.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorServiceImpl implements JugadorService {

    private final JugadorRepository repository;

    public JugadorServiceImpl(JugadorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Jugador> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Jugador> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Jugador guardar(Jugador jugador) {
        return repository.save(jugador);
    }

    @Override
    public Jugador actualizar(Integer id, Jugador jugador) {
        jugador.setIdJugador(id);
        return repository.save(jugador);
    }

    @Override
    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Jugador> jugadoresPorEquipo(Integer idEquipo) {
        return repository.findJugadoresByEquipo(idEquipo);
    }

    @Override
    public List<Jugador> jugadoresConMasDeXGoles(Integer minGoles) {
        return repository.findJugadoresConMasDeXGoles(minGoles);
    }
}