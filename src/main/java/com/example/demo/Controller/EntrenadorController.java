package com.example.demo.Controller;

import com.example.demo.Model.Entrenador;
import com.example.demo.Service.EntrenadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
@Tag(name = "Entrenadores", description = "CRUD de entrenadores")
public class EntrenadorController {

    private final EntrenadorService service;

    public EntrenadorController(EntrenadorService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todos los entrenadores")
    public ResponseEntity<List<Entrenador>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener entrenador por ID")
    public ResponseEntity<Entrenador> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo entrenador")
    public ResponseEntity<Entrenador> crear(@RequestBody Entrenador entrenador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(entrenador));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un entrenador")
    public ResponseEntity<Entrenador> actualizar(@PathVariable Integer id, @RequestBody Entrenador entrenador) {
        return ResponseEntity.ok(service.actualizar(id, entrenador));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un entrenador")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}