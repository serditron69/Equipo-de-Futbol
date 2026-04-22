package com.example.equipodefutbolapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.equipodefutbolapp.Model.Partido
import com.example.equipodefutbolapp.repository.PartidoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PartidoViewModel : ViewModel() {

    private val repository = PartidoRepository()

    private val _partidos = MutableStateFlow<List<Partido>>(emptyList())
    val partidos: StateFlow<List<Partido>> = _partidos

    private val _mensaje = MutableStateFlow<String?>(null)
    val mensaje: StateFlow<String?> = _mensaje

    fun getPartidos() {
        viewModelScope.launch {
            val response = repository.getPartidos()
            if (response.isSuccessful) {
                _partidos.value = response.body() ?: emptyList()
            } else {
                _mensaje.value = "Error al cargar partidos"
            }
        }
    }

    fun crearPartido(partido: Partido) {
        viewModelScope.launch {
            val response = repository.crearPartido(partido)
            if (response.isSuccessful) {
                _mensaje.value = "Partido creado correctamente"
                getPartidos()
            } else {
                _mensaje.value = "Error al crear partido"
            }
        }
    }

    fun eliminarPartido(id: Int) {
        viewModelScope.launch {
            val response = repository.eliminarPartido(id)
            if (response.isSuccessful) {
                _mensaje.value = "Partido eliminado correctamente"
                getPartidos()
            } else {
                _mensaje.value = "Error al eliminar partido"
            }
        }
    }
}