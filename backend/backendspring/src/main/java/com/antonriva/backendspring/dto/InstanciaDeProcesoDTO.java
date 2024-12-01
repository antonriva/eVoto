package com.antonriva.backendspring.dto;

import java.time.LocalDate;

public class InstanciaDeProcesoDTO {
    private Integer id;
    private LocalDate fechaDeInicio;
    private LocalDate fechaDeFin;
    private Integer ganadoresNum;
    private String descripcionNivel; // Datos del Nivel
    private String nombreProceso;   // Datos del Proceso

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getGanadoresNum() {
        return ganadoresNum;
    }

    public void setGanadoresNum(Integer ganadoresNum) {
        this.ganadoresNum = ganadoresNum;
    }

    public String getDescripcionNivel() {
        return descripcionNivel;
    }

    public void setDescripcionNivel(String descripcionNivel) {
        this.descripcionNivel = descripcionNivel;
    }

    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }
}
