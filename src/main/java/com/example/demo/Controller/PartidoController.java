package com.example.demo.Controller;

import com.example.demo.DTO.ResultadoPartidoDTO;
import com.example.demo.Model.Partido;
import com.example.demo.Service.PartidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidos")
@Tag(name = "Partidos", description = "CRUD de partidos y consultas nativas")
public class PartidoController {

    private final PartidoService service;

    public PartidoController(PartidoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todos los partidos")
    public ResponseEntity<List<Partido>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener partido por ID")
    public ResponseEntity<Partido> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo partido")
    public ResponseEntity<Partido> crear(@RequestBody Partido partido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(partido));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un partido")
    public ResponseEntity<Partido> actualizar(@PathVariable Integer id, @RequestBody Partido partido) {
        return ResponseEntity.ok(service.actualizar(id, partido));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un partido")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // ── Consulta Nativa ───────────────────────────────────────────────

    @GetMapping("/resultados")
    @Operation(summary = "[Consulta Nativa 4] Resultados de partidos con nombres de equipos")
    public ResponseEntity<List<ResultadoPartidoDTO>> resultados() {
        return ResponseEntity.ok(service.obtenerResultadosPartidos());
    }
}