package com.example.demo.Service;

import com.example.demo.DTO.VeterinarioDTO;
import java.util.List;
import java.util.Optional;

public interface VeterinarioService {
    List<VeterinarioDTO> getAll();
    VeterinarioDTO save(VeterinarioDTO dto);
    Optional<VeterinarioDTO> getById(Long id);
    void delete(Long id);
}
