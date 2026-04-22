package com.example.equipodefutbolapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.equipodefutbolapp.Model.EstadisticaJugador
import com.example.equipodefutbolapp.repository.EstadisticaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EstadisticaViewModel : ViewModel() {

    private val repository = EstadisticaRepository()

    private val _estadisticas = MutableStateFlow<List<EstadisticaJugador>>(emptyList())
    val estadisticas: StateFlow<List<EstadisticaJugador>> = _estadisticas

    private val _mensaje = MutableStateFlow<String?>(null)
    val mensaje: StateFlow<String?> = _mensaje

    fun getEstadisticas() {
        viewModelScope.launch {
            val response = repository.getEstadisticas()
            if (response.isSuccessful) {
                _estadisticas.value = response.body() ?: emptyList()
            } else {
                _mensaje.value = "Error al cargar estadísticas"
            }
        }
    }

    fun crearEstadistica(estadistica: EstadisticaJugador) {
        viewModelScope.launch {
            val response = repository.crearEstadistica(estadistica)
            if (response.isSuccessful) {
                _mensaje.value = "Estadística creada correctamente"
                getEstadisticas()
            } else {
                _mensaje.value = "Error al crear estadística"
            }
        }
    }

    fun eliminarEstadistica(id: Int) {
        viewModelScope.launch {
            val response = repository.eliminarEstadistica(id)
            if (response.isSuccessful) {
                _mensaje.value = "Estadística eliminada correctamente"
                getEstadisticas()
            } else {
                _mensaje.value = "Error al eliminar estadística"
            }
        }
    }

    fun totalGolesEquipo(idEquipo: Int) {
        viewModelScope.launch {
            val response = repository.totalGolesEquipo(idEquipo)
            if (response.isSuccessful) {
                _mensaje.value = "Total goles: ${response.body()}"
            } else {
                _mensaje.value = "Error en consulta nativa"
            }
        }
    }
}