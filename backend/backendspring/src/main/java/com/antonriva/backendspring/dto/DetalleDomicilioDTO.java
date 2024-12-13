package com.antonriva.backendspring.dto;

import java.time.LocalDate;

public class DetalleDomicilioDTO {
    private Long id;
    private String entidadFederativa;
    private String municipio;
    private String localidad;
    private String colonia;
    private String codigoPostal;
    private String calle;
    private String tipoDeDomicilio;
    private LocalDate fechaDeInicio;
    private LocalDate fechaDeFin;
    private Integer numeroExterior;
    private Integer numeroInterior;
    
    public DetalleDomicilioDTO() {
    	
    }
    
    public DetalleDomicilioDTO(Long id, String entidadFederativa, String municipio, String localidad, String colonia, String codigoPostal, String calle, String tipoDeDomicilio, LocalDate fechaDeInicio, LocalDate fechaDeFin, Integer numeroExterior, Integer numeroInterior) {
    	this.id = id;
    	this.entidadFederativa = entidadFederativa;
    	this.municipio = municipio;
    	this.localidad = localidad;
    	this.colonia = colonia;
    	this.codigoPostal = codigoPostal;
    	this.calle = calle;
    	this.tipoDeDomicilio = tipoDeDomicilio;
    	this.fechaDeFin = fechaDeFin;
    	this.fechaDeInicio = fechaDeInicio;
    	this.numeroExterior = numeroExterior;
    	this.numeroInterior = numeroInterior;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEntidadFederativa() {
		return entidadFederativa;
	}

	public void setEntidadFederativa(String entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
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

	public int getNumeroExterior() {
		return numeroExterior;
	}

	public void setNumeroExterior(Integer numeroExterior) {
		this.numeroExterior = numeroExterior;
	}

	public int getNumeroInterior() {
		return numeroInterior;
	}

	public void setNumeroInterior(Integer numeroInterior) {
		this.numeroInterior = numeroInterior;
	}

	public String getTipoDeDomicilio() {
		return tipoDeDomicilio;
	}

	public void setTipoDeDomicilio(String tipoDeDomicilio) {
		this.tipoDeDomicilio = tipoDeDomicilio;
	}

    
}