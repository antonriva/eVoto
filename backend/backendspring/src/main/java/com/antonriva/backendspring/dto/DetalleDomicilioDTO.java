package com.antonriva.backendspring.dto;

import java.time.LocalDate;

public class DetalleDomicilioDTO {
    private String calle;
    private int numeroExterior;
    private String municipio;
    private String colonia;
    private String entidadFederativa;
    private String codigoPostal;
    private LocalDate fechaDeInicio;
    private LocalDate fechaDeFin;
    
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getNumeroExterior() {
		return numeroExterior;
	}
	public void setNumeroExterior(int numeroExterior) {
		this.numeroExterior = numeroExterior;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getEntidadFederativa() {
		return entidadFederativa;
	}
	public void setEntidadFederativa(String entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
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
    
    
}