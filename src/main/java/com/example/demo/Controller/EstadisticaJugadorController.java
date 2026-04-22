package com.example.demo.Controller;

import com.example.demo.DTO.TotalGolesEquipoDTO;
import com.example.demo.Model.EstadisticaJugador;
import com.example.demo.Service.EstadisticaJugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estadisticas")
@Tag(name = "Estadísticas", description = "CRUD de estadísticas y consultas nativas")
public class EstadisticaJugadorController {

    private final EstadisticaJugadorService service;

    public EstadisticaJugadorController(EstadisticaJugadorService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todas las estadísticas")
    public ResponseEntity<List<EstadisticaJugador>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener estadística por ID")
    public ResponseEntity<EstadisticaJugador> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear una nueva estadística")
    public ResponseEntity<EstadisticaJugador> crear(@RequestBody EstadisticaJugador estadistica) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(estadistica));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una estadística")
    public ResponseEntity<EstadisticaJugador> actualizar(@PathVariable Integer id,
                                                         @RequestBody EstadisticaJugador estadistica) {
        return ResponseEntity.ok(service.actualizar(id, estadistica));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una estadística")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ── Consulta Nativa ───────────────────────────────────────────────

    @GetMapping("/total-goles-equipo/{idEquipo}")
    @Operation(summary = "[Consulta Nativa 3] Total de goles de un equipo en todos sus partidos")
    public ResponseEntity<TotalGolesEquipoDTO> totalGolesEquipo(@PathVariable Integer idEquipo) {
        return ResponseEntity.ok(service.obtenerTotalGolesEquipo(idEquipo));
    }
}