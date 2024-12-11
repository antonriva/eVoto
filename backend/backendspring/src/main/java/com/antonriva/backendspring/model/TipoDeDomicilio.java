package com.antonriva.backendspring.model;

import jakarta.persistence.*;

@Entity
@Table(name="tipodedomicilio")
public class TipoDeDomicilio {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="descripcion", nullable = false)
	private String descripcion;
	
	public TipoDeDomicilio () {
		
	}
	
	public TipoDeDomicilio (String descripcion) {
		this.descripcion = descripcion;
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

	@Override
	public String toString() {
		return "TipoDeDomicilio [id=" + id + ", descripcion=" + descripcion + "]";
	}

	
	
}
