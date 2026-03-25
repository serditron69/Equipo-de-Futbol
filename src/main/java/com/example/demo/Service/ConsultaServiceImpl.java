package com.example.demo.Service;

import com.example.demo.DTO.ConsultaDTO;
import com.example.demo.Model.Consulta;
import com.example.demo.Repository.ConsultaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository repository;

    public ConsultaServiceImpl(ConsultaRepository repository) {
        this.repository = repository;
    }

    private ConsultaDTO toDTO(Consulta c) {
        ConsultaDTO dto = new ConsultaDTO();
        dto.setFecha(c.getFecha());
        dto.setDiagnostico(c.getDiagnostico());
        dto.setTratamiento(c.getTratamiento());
        dto.setMascotaNombre(c.getMascota() != null ? c.getMascota().getNombre() : null);
        dto.setVeterinarioNombre(c.getVeterinario() != null ? c.getVeterinario().getNombre() : null);
        return dto;
    }

    private Consulta toEntity(ConsultaDTO dto) {
        Consulta c = new Consulta();
        c.setFecha(dto.getFecha());
        c.setDiagnostico(dto.getDiagnostico());
        c.setTratamiento(dto.getTratamiento());
        return c;
    }

    @Override
    public List<ConsultaDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ConsultaDTO save(ConsultaDTO dto) {
        if (dto == null) throw new IllegalStateException("La consulta no puede ser nula");
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public Optional<ConsultaDTO> getById(Long id) {
        Consulta consulta = repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Consulta con id " + id + " no existe"));
        return Optional.of(toDTO(consulta));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new IllegalStateException("Consulta con id " + id + " no existe");
        repository.deleteById(id);
    }
}
