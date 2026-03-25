package com.example.demo.Service;

import com.example.demo.DTO.DuenoDTO;
import java.util.List;
import java.util.Optional;

public interface DuenoService {
    List<DuenoDTO> getAll();
    DuenoDTO save(DuenoDTO dto);
    Optional<DuenoDTO> getById(Long id);
    void delete(Long id);
}
