package com.example.equipodefutbolapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.equipodefutbolapp.Model.Jugador
import com.example.equipodefutbolapp.repository.JugadorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JugadorViewModel : ViewModel() {

    private val repository = JugadorRepository()

    private val _jugadores = MutableStateFlow<List<Jugador>>(emptyList())
    val jugadores: StateFlow<List<Jugador>> = _jugadores

    private val _mensaje = MutableStateFlow<String?>(null)
    val mensaje: StateFlow<String?> = _mensaje

    fun getJugadores() {
        viewModelScope.launch {
            val response = repository.getJugadores()
            if (response.isSuccessful) {
                _jugadores.value = response.body() ?: emptyList()
            } else {
                _mensaje.value = "Error al cargar jugadores"
            }
        }
    }

    fun crearJugador(jugador: Jugador) {
        viewModelScope.launch {
            val response = repository.crearJugador(jugador)
            if (response.isSuccessful) {
                _mensaje.value = "Jugador creado correctamente"
                getJugadores()
            } else {
                _mensaje.value = "Error al crear jugador"
            }
        }
    }

    fun actualizarJugador(id: Int, jugador: Jugador) {
        viewModelScope.launch {
            val response = repository.actualizarJugador(id, jugador)
            if (response.isSuccessful) {
                _mensaje.value = "Jugador actualizado correctamente"
                getJugadores()
            } else {
                _mensaje.value = "Error al actualizar jugador"
            }
        }
    }

    fun eliminarJugador(id: Int) {
        viewModelScope.launch {
            val response = repository.eliminarJugador(id)
            if (response.isSuccessful) {
                _mensaje.value = "Jugador eliminado correctamente"
                getJugadores()
            } else {
                _mensaje.value = "Error al eliminar jugador"
            }
        }
    }

    fun jugadoresPorEquipo(idEquipo: Int) {
        viewModelScope.launch {
            val response = repository.jugadoresPorEquipo(idEquipo)
            if (response.isSuccessful) {
                _jugadores.value = response.body() ?: emptyList()
            } else {
                _mensaje.value = "Error en consulta nativa"
            }
        }
    }

    fun jugadoresConMasDeXGoles(goles: Int) {
        viewModelScope.launch {
            val response = repository.jugadoresConMasDeXGoles(goles)
            if (response.isSuccessful) {
                _jugadores.value = response.body() ?: emptyList()
            } else {
                _mensaje.value = "Error en consulta nativa"
            }
        }
    }
}