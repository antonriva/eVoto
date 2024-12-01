package com.antonriva.backendspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "Partido")
public class Partido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="DENOMINACION")
	private String denominacion;
	
	@Column(name="SIGLAS")
	private String siglas;
	
	public Partido() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Partido(int id, String denominacion, String siglas) {
		super();
		this.id = id;
		this.denominacion = denominacion;
		this.siglas = siglas;
	}

	@Override
	public String toString() {
		return "Partido [id=" + id + ", denominacion=" + denominacion + ", siglas=" + siglas + "]";
	}
	
	
	
}
