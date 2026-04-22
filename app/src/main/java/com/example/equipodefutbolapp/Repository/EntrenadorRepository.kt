package com.example.equipodefutbolapp.repository

import com.example.equipodefutbolapp.Interfaces.RetrofitClient
import com.example.equipodefutbolapp.Model.Entrenador

class EntrenadorRepository {
    private val api = RetrofitClient.instance

    suspend fun getEntrenadores() = api.getEntrenadores()
    suspend fun crearEntrenador(entrenador: Entrenador) = api.crearEntrenador(entrenador)
    suspend fun actualizarEntrenador(id: Int, entrenador: Entrenador) = api.actualizarEntrenador(id, entrenador)
    suspend fun eliminarEntrenador(id: Int) = api.eliminarEntrenador(id)
}