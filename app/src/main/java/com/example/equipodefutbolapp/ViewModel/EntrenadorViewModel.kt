package com.example.equipodefutbolapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.equipodefutbolapp.Model.Entrenador
import com.example.equipodefutbolapp.repository.EntrenadorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EntrenadorViewModel : ViewModel() {

    private val repository = EntrenadorRepository()

    private val _entrenadores = MutableStateFlow<List<Entrenador>>(emptyList())
    val entrenadores: StateFlow<List<Entrenador>> = _entrenadores

    private val _mensaje = MutableStateFlow<String?>(null)
    val mensaje: StateFlow<String?> = _mensaje

    fun getEntrenadores() {
        viewModelScope.launch {
            val response = repository.getEntrenadores()
            if (response.isSuccessful) {
                _entrenadores.value = response.body() ?: emptyList()
            } else {
                _mensaje.value = "Error al cargar entrenadores"
            }
        }
    }

    fun crearEntrenador(entrenador: Entrenador) {
        viewModelScope.launch {
            val response = repository.crearEntrenador(entrenador)
            if (response.isSuccessful) {
                _mensaje.value = "Entrenador creado correctamente"
                getEntrenadores()
            } else {
                _mensaje.value = "Error al crear entrenador"
            }
        }
    }

    fun actualizarEntrenador(id: Int, entrenador: Entrenador) {
        viewModelScope.launch {
            val response = repository.actualizarEntrenador(id, entrenador)
            if (response.isSuccessful) {
                _mensaje.value = "Entrenador actualizado correctamente"
                getEntrenadores()
            } else {
                _mensaje.value = "Error al actualizar entrenador"
            }
        }
    }

    fun eliminarEntrenador(id: Int) {
        viewModelScope.launch {
            val response = repository.eliminarEntrenador(id)
            if (response.isSuccessful) {
                _mensaje.value = "Entrenador eliminado correctamente"
                getEntrenadores()
            } else {
                _mensaje.value = "Error al eliminar entrenador"
            }
        }
    }
}