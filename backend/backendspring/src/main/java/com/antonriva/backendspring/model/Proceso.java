package com.antonriva.backendspring.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "PROCESO")
public class Proceso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "proceso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference // Evita la serialización cíclica
    private List<NivelProceso> nivelProcesos;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<NivelProceso> getNivelProcesos() {
        return nivelProcesos;
    }

    public void setNivelProcesos(List<NivelProceso> nivelProcesos) {
        this.nivelProcesos = nivelProcesos;
    }
}

