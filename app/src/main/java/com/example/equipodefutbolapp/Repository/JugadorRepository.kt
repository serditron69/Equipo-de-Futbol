package com.example.equipodefutbolapp.repository

import com.example.equipodefutbolapp.Interfaces.RetrofitClient
import com.example.equipodefutbolapp.Model.Jugador

class JugadorRepository {
    private val api = RetrofitClient.instance

    suspend fun getJugadores() = api.getJugadores()
    suspend fun getJugadorPorId(id: Int) = api.getJugadorPorId(id)
    suspend fun crearJugador(jugador: Jugador) = api.crearJugador(jugador)
    suspend fun actualizarJugador(id: Int, jugador: Jugador) = api.actualizarJugador(id, jugador)
    suspend fun eliminarJugador(id: Int) = api.eliminarJugador(id)
    suspend fun jugadoresPorEquipo(idEquipo: Int) = api.jugadoresPorEquipo(idEquipo)
    suspend fun jugadoresConMasDeXGoles(goles: Int) = api.jugadoresConMasDeXGoles(goles)
}