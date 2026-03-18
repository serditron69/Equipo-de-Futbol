package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cursos")

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(nullable = false)
    private int creditos;


    @ManyToMany(mappedBy = "cursos")
    @JsonIgnoreProperties("cursos")
    private Set<Estudiante> estudiante = new HashSet<>();

    public Curso() {
    }

    public Curso(Long id, String nombre, int creditos, Set<Estudiante> estudiante) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
        this.estudiante = estudiante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public Set<Estudiante> getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Set<Estudiante> estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", creditos=" + creditos +
                ", estudiante=" + estudiante +
                '}';
    }
}
