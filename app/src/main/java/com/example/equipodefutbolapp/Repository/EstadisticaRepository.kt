package com.example.equipodefutbolapp.repository

import com.example.equipodefutbolapp.Interfaces.RetrofitClient
import com.example.equipodefutbolapp.Model.EstadisticaJugador

class EstadisticaRepository {
    private val api = RetrofitClient.instance

    suspend fun getEstadisticas() = api.getEstadisticas()
    suspend fun crearEstadistica(estadistica: EstadisticaJugador) = api.crearEstadistica(estadistica)
    suspend fun actualizarEstadistica(id: Int, estadistica: EstadisticaJugador) = api.actualizarEstadistica(id, estadistica)
    suspend fun eliminarEstadistica(id: Int) = api.eliminarEstadistica(id)
    suspend fun totalGolesEquipo(idEquipo: Int) = api.totalGolesEquipo(idEquipo)
}
