package com.example.equipodefutbolapp.repository

import com.example.equipodefutbolapp.Interfaces.RetrofitClient
import com.example.equipodefutbolapp.Model.Partido

class PartidoRepository {
    private val api = RetrofitClient.instance

    suspend fun getPartidos() = api.getPartidos()
    suspend fun crearPartido(partido: Partido) = api.crearPartido(partido)
    suspend fun actualizarPartido(id: Int, partido: Partido) = api.actualizarPartido(id, partido)
    suspend fun eliminarPartido(id: Int) = api.eliminarPartido(id)
    suspend fun resultadosPartidos() = api.resultadosPartidos()
}