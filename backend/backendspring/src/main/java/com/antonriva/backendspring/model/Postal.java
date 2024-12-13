package com.antonriva.backendspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="postal")
public class Postal {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iddecolonia", nullable = false)
	@JsonIgnore
	private Colonia colonia;
	
	@Column(name="descripcion", nullable = false)
	private String descripcion;
	
	public Postal () {
		
	}
	
	public Postal (String descripcion, Colonia colonia ) {
		this.descripcion = descripcion;
		this.colonia = colonia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Colonia getColonia() {
		return colonia;
	}

	public void setColonia(Colonia colonia) {
		this.colonia = colonia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Postal [id=" + id + ", descripcion=" + descripcion + "]";
	}

	
	
}
