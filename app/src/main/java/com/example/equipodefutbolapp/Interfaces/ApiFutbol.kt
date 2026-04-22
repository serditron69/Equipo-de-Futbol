package com.example.equipodefutbolapp.Interfaces

import com.example.equipodefutbolapp.Model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiFutbol {

    // ── Equipos ──────────────────────────────────────────────
    @GET("api/equipos")
    suspend fun getEquipos(): Response<List<Equipo>>

    @GET("api/equipos/{id}")
    suspend fun getEquipoPorId(@Path("id") id: Int): Response<Equipo>

    @POST("api/equipos")
    suspend fun crearEquipo(@Body equipo: CrearEquipoRequest): Response<Equipo>

    @PUT("api/equipos/{id}")
    suspend fun actualizarEquipo(@Path("id") id: Int, @Body equipo: Equipo): Response<Equipo>

    @DELETE("api/equipos/{id}")
    suspend fun eliminarEquipo(@Path("id") id: Int): Response<Void>

    // ── Jugadores ─────────────────────────────────────────────
    @GET("api/jugadores")
    suspend fun getJugadores(): Response<List<Jugador>>

    @GET("api/jugadores/{id}")
    suspend fun getJugadorPorId(@Path("id") id: Int): Response<Jugador>

    @POST("api/jugadores")
    suspend fun crearJugador(@Body jugador: Jugador): Response<Jugador>

    @PUT("api/jugadores/{id}")
    suspend fun actualizarJugador(@Path("id") id: Int, @Body jugador: Jugador): Response<Jugador>

    @DELETE("api/jugadores/{id}")
    suspend fun eliminarJugador(@Path("id") id: Int): Response<Void>

    // ── Entrenadores ──────────────────────────────────────────
    @GET("api/entrenadores")
    suspend fun getEntrenadores(): Response<List<Entrenador>>

    @POST("api/entrenadores")
    suspend fun crearEntrenador(@Body entrenador: Entrenador): Response<Entrenador>

    @PUT("api/entrenadores/{id}")
    suspend fun actualizarEntrenador(@Path("id") id: Int, @Body entrenador: Entrenador): Response<Entrenador>

    @DELETE("api/entrenadores/{id}")
    suspend fun eliminarEntrenador(@Path("id") id: Int): Response<Void>

    // ── Partidos ──────────────────────────────────────────────
    @GET("api/partidos")
    suspend fun getPartidos(): Response<List<Partido>>

    @POST("api/partidos")
    suspend fun crearPartido(@Body partido: Partido): Response<Partido>

    @PUT("api/partidos/{id}")
    suspend fun actualizarPartido(@Path("id") id: Int, @Body partido: Partido): Response<Partido>

    @DELETE("api/partidos/{id}")
    suspend fun eliminarPartido(@Path("id") id: Int): Response<Void>

    // ── Estadísticas ──────────────────────────────────────────
    @GET("api/estadisticas")
    suspend fun getEstadisticas(): Response<List<EstadisticaJugador>>

    @POST("api/estadisticas")
    suspend fun crearEstadistica(@Body estadistica: EstadisticaJugador): Response<EstadisticaJugador>

    @PUT("api/estadisticas/{id}")
    suspend fun actualizarEstadistica(@Path("id") id: Int, @Body estadistica: EstadisticaJugador): Response<EstadisticaJugador>

    @DELETE("api/estadisticas/{id}")
    suspend fun eliminarEstadistica(@Path("id") id: Int): Response<Void>

    // ── Consultas Nativas ─────────────────────────────────────
    @GET("api/jugadores/equipo/{idEquipo}")
    suspend fun jugadoresPorEquipo(@Path("idEquipo") idEquipo: Int): Response<List<Jugador>>

    @GET("api/jugadores/mas-de-goles")
    suspend fun jugadoresConMasDeXGoles(@Query("goles") goles: Int): Response<List<Jugador>>

    @GET("api/estadisticas/total-goles-equipo/{idEquipo}")
    suspend fun totalGolesEquipo(@Path("idEquipo") idEquipo: Int): Response<Map<String, Any>>

    @GET("api/partidos/resultados")
    suspend fun resultadosPartidos(): Response<List<Map<String, Any>>>
}