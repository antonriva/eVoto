package com.antonriva.backendspring.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Pattern;



public class PersonaEditarDTO {

    @Pattern(regexp = "^[A-Z]+( [A-Z]+)*$", message = "El nombre debe contener solo letras mayúsculas y espacios, sin números ni caracteres especiales.")
    private String nombre;

    @Pattern(regexp = "^[A-Z]+$", message = "El apellido paterno debe contener solo letras mayúsculas, sin números ni caracteres especiales.")
    private String apellidoPaterno;

    @Pattern(regexp = "^[A-Z]+$", message = "El apellido materno debe contener solo letras mayúsculas, sin números ni caracteres especiales.")
    private String apellidoMaterno;

    private LocalDate fechaDeNacimiento;
    private LocalDate fechaDeFin;
    private Long entidadFederativaId; // ID de la Entidad Federativa
    private Long municipioId;         // ID del Municipio
    private LocalDate fechaDeInicio;

    public PersonaEditarDTO(String nombre, String apellidoPaterno, String apellidoMaterno,
                            LocalDate fechaDeNacimiento, LocalDate fechaDeFin, Long entidadFederativaId, Long municipioId, LocalDate fechaDeInicio) {
        this.nombre = normalizeName(nombre);
        this.apellidoPaterno = normalizeSingleWord(apellidoPaterno);
        this.apellidoMaterno = normalizeSingleWord(apellidoMaterno);
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaDeFin = fechaDeFin;
        this.entidadFederativaId = entidadFederativaId;
        this.municipioId = municipioId;
        this.fechaDeInicio = fechaDeInicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = normalizeName(nombre);
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = normalizeSingleWord(apellidoPaterno);
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = normalizeSingleWord(apellidoMaterno);
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public LocalDate getFechaDeFin() {
        return fechaDeFin;
    }

    public void setFechaDeFin(LocalDate fechaDeFin) {
        this.fechaDeFin = fechaDeFin;
    }

    public Long getEntidadFederativaId() {
        return entidadFederativaId;
    }

    public void setEntidadFederativaId(Long entidadFederativaId) {
        this.entidadFederativaId = entidadFederativaId;
    }

    public Long getMunicipioId() {
        return municipioId;
    }

    public void setMunicipioId(Long municipioId) {
        this.municipioId = municipioId;
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
