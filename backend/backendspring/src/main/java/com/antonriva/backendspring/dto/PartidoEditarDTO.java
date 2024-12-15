package com.antonriva.backendspring.dto;

import java.time.LocalDate;

public class PartidoEditarDTO {
    private Long id;
    private String denominacion;
    private String siglas;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String visualUrl;

    public PartidoEditarDTO(Long id, String denominacion, String siglas, LocalDate fechaInicio, LocalDate fechaFin, String visualUrl) {
        this.id = id;
        this.denominacion = denominacion;
        this.siglas = siglas;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
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
		this.denominacion = denominacion;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getVisualUrl() {
		return visualUrl;
	}

	public void setVisualUrl(String visualUrl) {
		this.visualUrl = visualUrl;
	}

    
}
