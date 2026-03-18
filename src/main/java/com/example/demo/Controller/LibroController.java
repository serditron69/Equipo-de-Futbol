package com.example.demo.Controller;

import com.example.demo.Model.Libro;
import com.example.demo.Service.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService service;

    public LibroController(LibroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Libro> create(@RequestBody Libro libro){
        return ResponseEntity.status(201).body(service.save(libro));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Libro> update(@PathVariable Long id, @RequestBody Libro libro){
        libro.setId(id);
        return ResponseEntity.ok(service.save(libro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
