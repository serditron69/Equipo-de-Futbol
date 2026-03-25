package com.example.demo.Service;

import com.example.demo.DTO.ConsultaDTO;
import java.util.List;
import java.util.Optional;

public interface ConsultaService {
    List<ConsultaDTO> getAll();
    ConsultaDTO save(ConsultaDTO dto);
    Optional<ConsultaDTO> getById(Long id);
    void delete(Long id);
}
