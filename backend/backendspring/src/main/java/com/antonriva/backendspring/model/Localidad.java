package com.antonriva.backendspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="localidad")
public class Localidad {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iddemunicipio", nullable = false)
	@JsonIgnore
	private Municipio municipio;
	
	
	@Column(name="descripcion", nullable = false)
	private String descripcion;
	

	public Localidad () {
		
	}
	
	public Localidad (String descripcion, Municipio municipio ) {
		this.descripcion = descripcion;
		this.municipio = municipio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@Override
	public String toString() {
		return "Localidad [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
	
	
	

}
