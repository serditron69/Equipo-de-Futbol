package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "categorias")

public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 80)
    private String name;

    @Column(nullable = false, length = 150)
    private String description;

    // una categoria tiene muchos libros
    //mappedBy - fk de la relacion
    //cascade - eliminacion
    //orphanRemoval - si borra la categoria no borra los libros

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Libro> libros = new ArrayList<Libro>();

    public Categoria() {
    }

    public Categoria(Long id, String name, String description, List<Libro> libros) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.libros = libros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", libros=" + libros +
                '}';
    }


}
