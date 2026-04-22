package com.example.equipodefutbolapp.Model

data class Entrenador(
    val idEntrenador: Int,
    val nombre: String,
    val especialidad: String,
    val equipo: Equipo
)