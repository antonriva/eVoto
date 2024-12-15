package com.antonriva.backendspring.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PartidoRegistrarDTO {
	
    @Pattern(regexp = "^[A-Z]+( [A-Z]+)*$", message = "La denominación debe contener solo letras mayúsculas y espacios, sin números ni caracteres especiales.")
    private String denominacion;

    @Pattern(regexp = "^[A-Z]+$", message = "Las siglas deben contener solo letras mayúsculas, sin números ni caracteres especiales.")
    private String siglas;

    private LocalDate fechaDeInicio;

    // Constructor
    public PartidoRegistrarDTO(String denominacion, String siglas, LocalDate fechaDeInicio) {
        this.denominacion = normalizeName(denominacion);
        this.siglas = normalizeSingleWord(siglas);
        this.fechaDeInicio = fechaDeInicio;
    }

    // Getters y Setters
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
