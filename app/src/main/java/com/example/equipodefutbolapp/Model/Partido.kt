package com.example.equipodefutbolapp.Model

data class Partido(
    val idPartido: Int,
    val fecha: String,
    val estadio: String,
    val equipoLocal: Equipo,
    val equipoVisita: Equipo,
    val golesLocal: Int,
    val golesVisita: Int
)