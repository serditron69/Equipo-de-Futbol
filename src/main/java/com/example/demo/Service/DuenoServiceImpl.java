package com.example.demo.Service;

import com.example.demo.DTO.DuenoDTO;
import com.example.demo.Model.Dueno;
import com.example.demo.Repository.DuenoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DuenoServiceImpl implements DuenoService {

    private final DuenoRepository repository;

    public DuenoServiceImpl(DuenoRepository repository) {
        this.repository = repository;
    }

    private DuenoDTO toDTO(Dueno d) {
        DuenoDTO dto = new DuenoDTO();
        dto.setNombre(d.getNombre());
        dto.setTelefono(d.getTelefono());
        dto.setDireccion(d.getDireccion());
        return dto;
    }

    private Dueno toEntity(DuenoDTO dto) {
        Dueno d = new Dueno();
        d.setNombre(dto.getNombre());
        d.setTelefono(dto.getTelefono());
        d.setDireccion(dto.getDireccion());
        return d;
    }

    @Override
    public List<DuenoDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DuenoDTO save(DuenoDTO dto) {
        if (dto == null) throw new IllegalStateException("El dueño no puede ser nulo");
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public Optional<DuenoDTO> getById(Long id) {
        Dueno dueno = repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Dueño con id " + id + " no existe"));
        return Optional.of(toDTO(dueno));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new IllegalStateException("Dueño con id " + id + " no existe");
        repository.deleteById(id);
    }
}
