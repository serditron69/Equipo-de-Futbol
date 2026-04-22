package com.example.demo.Controller;

import com.example.demo.Model.Jugador;
import com.example.demo.Service.JugadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
@Tag(name = "Jugadores", description = "CRUD de jugadores y consultas nativas")
public class JugadorController {

    private final JugadorService service;

    public JugadorController(JugadorService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todos los jugadores")
    public ResponseEntity<List<Jugador>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener jugador por ID")
    public ResponseEntity<Jugador> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo jugador")
    public ResponseEntity<Jugador> crear(@RequestBody Jugador jugador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(jugador));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un jugador")
    public ResponseEntity<Jugador> actualizar(@PathVariable Integer id, @RequestBody Jugador jugador) {
        return ResponseEntity.ok(service.actualizar(id, jugador));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un jugador")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ── Consultas Nativas ─────────────────────────────────────────────

    @GetMapping("/equipo/{idEquipo}")
    @Operation(summary = "[Consulta Nativa 1] Jugadores de un equipo específico")
    public ResponseEntity<List<Jugador>> jugadoresPorEquipo(@PathVariable Integer idEquipo) {
        return ResponseEntity.ok(service.jugadoresPorEquipo(idEquipo));
    }

    @GetMapping("/mas-de-goles")
    @Operation(summary = "[Consulta Nativa 2] Jugadores con más de X goles")
    public ResponseEntity<List<Jugador>> jugadoresConMasDeXGoles(@RequestParam Integer goles) {
        return ResponseEntity.ok(service.jugadoresConMasDeXGoles(goles));
    }
}