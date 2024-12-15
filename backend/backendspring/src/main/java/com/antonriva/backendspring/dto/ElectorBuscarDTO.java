package com.antonriva.backendspring.dto;

import java.time.LocalDate;

public class ElectorBuscarDTO {
    private Long idDeElector;
    private Long idDePersona;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaDeFin;

    public ElectorBuscarDTO(Long idDeElector, Long idDePersona, String nombre, String apellidoPaterno,
                            String apellidoMaterno, LocalDate fechaDeNacimiento, LocalDate fechaDeFin) {
        this.idDeElector = idDeElector;
        this.idDePersona = idDePersona;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaDeFin = fechaDeFin;
    }

    // Getters y Setters
    public Long getIdDeElector() {
        return idDeElector;
    }

    public void setIdDeElector(Long idDeElector) {
        this.idDeElector = idDeElector;
    }

    public Long getIdDePersona() {
        return idDePersona;
    }

    public void setIdDePersona(Long idDePersona) {
        this.idDePersona = idDePersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
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
}

