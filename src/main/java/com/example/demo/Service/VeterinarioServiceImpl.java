package com.example.demo.Service;

import com.example.demo.DTO.VeterinarioDTO;
import com.example.demo.Model.Veterinario;
import com.example.demo.Repository.VeterinarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    private final VeterinarioRepository repository;

    public VeterinarioServiceImpl(VeterinarioRepository repository) {
        this.repository = repository;
    }

    private VeterinarioDTO toDTO(Veterinario v) {
        VeterinarioDTO dto = new VeterinarioDTO();
        dto.setNombre(v.getNombre());
        dto.setEspecialidad(v.getEspecialidad());
        return dto;
    }

    private Veterinario toEntity(VeterinarioDTO dto) {
        Veterinario v = new Veterinario();
        v.setNombre(dto.getNombre());
        v.setEspecialidad(dto.getEspecialidad());
        return v;
    }

    @Override
    public List<VeterinarioDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VeterinarioDTO save(VeterinarioDTO dto) {
        if (dto == null) throw new IllegalStateException("El veterinario no puede ser nulo");
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public Optional<VeterinarioDTO> getById(Long id) {
        Veterinario veterinario = repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Veterinario con id " + id + " no existe"));
        return Optional.of(toDTO(veterinario));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new IllegalStateException("Veterinario con id " + id + " no existe");
        repository.deleteById(id);
    }
}
