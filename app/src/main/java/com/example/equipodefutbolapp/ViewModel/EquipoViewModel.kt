package com.example.equipodefutbolapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.equipodefutbolapp.Model.Equipo
import com.example.equipodefutbolapp.Model.CrearEquipoRequest
import com.example.equipodefutbolapp.repository.EquipoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EquipoViewModel : ViewModel() {

    private val repository = EquipoRepository()

    private val _equipos = MutableStateFlow<List<Equipo>>(emptyList())
    val equipos: StateFlow<List<Equipo>> = _equipos

    private val _mensaje = MutableStateFlow<String?>(null)
    val mensaje: StateFlow<String?> = _mensaje

    fun getEquipos() {
        viewModelScope.launch {
            val response = repository.getEquipos()
            if (response.isSuccessful) {
                _equipos.value = response.body() ?: emptyList()
            } else {
                _mensaje.value = "Error al cargar equipos"
            }
        }
    }

    fun crearEquipo(nombre: String, ciudad: String, fundacion: String) {
        viewModelScope.launch {
            val response = repository.crearEquipo(
                CrearEquipoRequest(nombre, ciudad, fundacion)
            )
            if (response.isSuccessful) {
                _mensaje.value = "Equipo creado correctamente"
                getEquipos()
            } else {
                _mensaje.value = "Error: ${response.code()} - ${response.errorBody()?.string()}"
            }
        }
    }

    fun actualizarEquipo(id: Int, equipo: Equipo) {
        viewModelScope.launch {
            val response = repository.actualizarEquipo(id, equipo)
            if (response.isSuccessful) {
                _mensaje.value = "Equipo actualizado correctamente"
                getEquipos()
            } else {
                _mensaje.value = "Error al actualizar equipo"
            }
        }
    }

    fun eliminarEquipo(id: Int) {
        viewModelScope.launch {
            val response = repository.eliminarEquipo(id)
            if (response.isSuccessful) {
                _mensaje.value = "Equipo eliminado correctamente"
                getEquipos()
            } else {
                _mensaje.value = "Error al eliminar equipo"
            }
        }
    }
}