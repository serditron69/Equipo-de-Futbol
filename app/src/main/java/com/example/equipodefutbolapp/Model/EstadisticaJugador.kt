package com.example.equipodefutbolapp.Model

data class EstadisticaJugador(
    val idEstadistica: Int,
    val jugador: Jugador,
    val partido: Partido,
    val minutosJugados: Int,
    val goles: Int,
    val asistencias: Int,
    val tarjetasAmarillas: Int,
    val tarjetasRojas: Int
)