package com.example.demo.Service;

import com.example.demo.DTO.MascotaDTO;
import java.util.List;
import java.util.Optional;

public interface MascotaService {
    List<MascotaDTO> getAll();
    MascotaDTO save(MascotaDTO dto);
    Optional<MascotaDTO> getById(Long id);
    void delete(Long id);
}
