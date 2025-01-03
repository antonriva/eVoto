package com.antonriva.backendspring.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public class PartidoEditarDTO {

    private Long id;

    @Pattern(regexp = "^[A-Z]+( [A-Z]+)*$", message = "La denominación debe contener solo letras mayúsculas y espacios, sin números ni caracteres especiales.")
    private String denominacion;

    @Pattern(regexp = "^[A-Z]+$", message = "Las siglas deben contener solo letras mayúsculas, sin números ni caracteres especiales.")
    private String siglas;

    private LocalDate fechaDeInicio;
    private LocalDate fechaDeFin;
    private String visualUrl;

    public PartidoEditarDTO(Long id, String denominacion, String siglas, LocalDate fechaDeInicio, LocalDate fechaDeFin, String visualUrl) {
        this.id = id;
        this.denominacion = normalizeName(denominacion);
        this.siglas = normalizeSingleWord(siglas);
        this.fechaDeInicio = fechaDeInicio;
        this.fechaDeFin = fechaDeFin;
        this.visualUrl = visualUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = normalizeName(denominacion);
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = normalizeSingleWord(siglas);
    }

    public LocalDate getFechaDeInicio() {
        return fechaDeInicio;
    }

    public void setFechaDeInicio(LocalDate fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }

    public LocalDate getFechaDeFin() {
        return fechaDeFin;
    }

    public void setFechaDeFin(LocalDate fechaDeFin) {
        this.fechaDeFin = fechaDeFin;
    }

    public String getVisualUrl() {
        return visualUrl;
    }

    public void setVisualUrl(String visualUrl) {
        this.visualUrl = visualUrl;
    }

    // Métodos auxiliares para normalizar los campos
    private String normalizeName(String value) {
        if (value == null) {
            return null;
        }
        return value.trim().replaceAll("\\s{2,}", " "); // Elimina espacios extra entre palabras
    }

    private String normalizeSingleWord(String value) {
        if (value == null) {
            return null;
        }
        return value.trim(); // Solo elimina espacios al inicio y final
    }
}

    
