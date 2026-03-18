package com.example.demo.Controller;

import com.example.demo.Model.Estudiante;
import com.example.demo.Service.EstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Estudiante>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Estudiante> create(@RequestBody Estudiante estudiante){
        return ResponseEntity.status(201).body(service.save(estudiante));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Estudiante> update(@PathVariable Long id, @RequestBody Estudiante estudiante){
        estudiante.setId(id);
        return ResponseEntity.ok(service.save(estudiante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
