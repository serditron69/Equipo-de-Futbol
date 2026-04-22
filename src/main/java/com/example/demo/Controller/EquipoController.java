package com.example.demo.Controller;

import com.example.demo.Model.Equipo;
import com.example.demo.Service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@Tag(name = "Equipos", description = "CRUD de equipos de fútbol")
public class EquipoController {

    private final EquipoService service;

    public EquipoController(EquipoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todos los equipos")
    public ResponseEntity<List<Equipo>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener equipo por ID")
    public ResponseEntity<Equipo> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo equipo")
    public ResponseEntity<Equipo> crear(@RequestBody Equipo equipo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(equipo));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un equipo")
    public ResponseEntity<Equipo> actualizar(@PathVariable Integer id, @RequestBody Equipo equipo) {
        return ResponseEntity.ok(service.actualizar(id, equipo));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un equipo")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}