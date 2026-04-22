package com.example.equipodefutbolapp.Model

data class Jugador(
    val idJugador: Int,
    val nombre: String,
    val posicion: String,
    val dorsal: Int,
    val fechaNac: String,
    val nacionalidad: String,
    val equipo: Equipo
)