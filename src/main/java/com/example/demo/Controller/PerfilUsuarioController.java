package com.example.demo.Controller;

import com.example.demo.Model.PerfilUsuario;
import com.example.demo.Service.PerfilUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/perfilesusuarios")
public class PerfilUsuarioController {

    private final PerfilUsuarioService service;

    public PerfilUsuarioController(PerfilUsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PerfilUsuario>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<PerfilUsuario> create(@RequestBody PerfilUsuario perfil){
        return ResponseEntity.status(201).body(service.save(perfil));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PerfilUsuario> update(@PathVariable Long id, @RequestBody PerfilUsuario perfil){
        perfil.setId(id);
        return ResponseEntity.ok(service.save(perfil));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
