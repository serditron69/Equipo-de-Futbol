package com.example.demo.DTO;

import java.time.LocalDate;

public interface ResultadoPartidoDTO {
    LocalDate getFecha();
    String getEquipoLocal();
    String getEquipoVisitante();
    Integer getGolesLocal();
    Integer getGolesVisita();
}