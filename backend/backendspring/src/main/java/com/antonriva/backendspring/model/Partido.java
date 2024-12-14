package com.antonriva.backendspring.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "partido")
public class Partido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="denominacion", nullable = false)
	private String denominacion;

	@Column(name="siglas", nullable = false)
	private String siglas;

	@Column(name="fechadeinicio", nullable = false)
	private LocalDate fechaDeInicio;

	@Column(name="fechadefin", nullable = true)
	private LocalDate fechaDeFin;
	
	public Partido() {
		
	}

	public Partido(Long id, String denominacion, String siglas, LocalDate fechaDeInicio) {
		super();
		this.id = id;
		this.denominacion = denominacion;
		this.siglas = siglas;
		this.fechaDeInicio = fechaDeInicio;
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

	@Override
	public String toString() {
		return "Partido [id=" + id + ", denominacion=" + denominacion + ", siglas=" + siglas + ", fechaDeInicio="
				+ fechaDeInicio + ", fechaDeFin=" + fechaDeFin + "]";
	}
	
	
	

}
