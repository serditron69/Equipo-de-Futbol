package com.example.equipodefutbolapp.repository

import com.example.equipodefutbolapp.Interfaces.RetrofitClient
import com.example.equipodefutbolapp.Model.Equipo
import com.example.equipodefutbolapp.Model.CrearEquipoRequest

class EquipoRepository {
    private val api = RetrofitClient.instance

    suspend fun getEquipos() = api.getEquipos()
    suspend fun getEquipoPorId(id: Int) = api.getEquipoPorId(id)
    suspend fun crearEquipo(equipo: CrearEquipoRequest) = api.crearEquipo(equipo)
    suspend fun actualizarEquipo(id: Int, equipo: Equipo) = api.actualizarEquipo(id, equipo)
    suspend fun eliminarEquipo(id: Int) = api.eliminarEquipo(id)
}