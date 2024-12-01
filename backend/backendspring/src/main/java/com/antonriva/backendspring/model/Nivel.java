package com.antonriva.backendspring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "NIVEL")
public class Nivel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "nivel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference // Evita la serialización cíclica
    private List<NivelProceso> nivelProcesos;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<NivelProceso> getNivelProcesos() {
        return nivelProcesos;
    }

    public void setNivelProcesos(List<NivelProceso> nivelProcesos) {
        this.nivelProcesos = nivelProcesos;
    }
}
