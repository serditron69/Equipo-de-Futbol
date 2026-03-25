package com.example.demo.Service;

import com.example.demo.DTO.MascotaDTO;
import com.example.demo.Model.Mascota;
import com.example.demo.Repository.MascotaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository repository;

    public MascotaServiceImpl(MascotaRepository repository) {
        this.repository = repository;
    }

    private MascotaDTO toDTO(Mascota m) {
        MascotaDTO dto = new MascotaDTO();
        dto.setNombre(m.getNombre());
        dto.setEspecie(m.getEspecie());
        dto.setRaza(m.getRaza());
        dto.setEdad(m.getEdad());
        dto.setDuenoNombre(m.getDueno() != null ? m.getDueno().getNombre() : null);
        return dto;
    }

    private Mascota toEntity(MascotaDTO dto) {
        Mascota m = new Mascota();
        m.setNombre(dto.getNombre());
        m.setEspecie(dto.getEspecie());
        m.setRaza(dto.getRaza());
        m.setEdad(dto.getEdad());
        return m;
    }

    @Override
    public List<MascotaDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MascotaDTO save(MascotaDTO dto) {
        if (dto == null) throw new IllegalStateException("La mascota no puede ser nula");
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public Optional<MascotaDTO> getById(Long id) {
        Mascota mascota = repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Mascota con id " + id + " no existe"));
        return Optional.of(toDTO(mascota));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new IllegalStateException("Mascota con id " + id + " no existe");
        repository.deleteById(id);
    }
}
