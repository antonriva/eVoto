package com.antonriva.backendspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "proceso")
public class Proceso {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@Column(name="descripcion", nullable=false)
    private String descripcion;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddenivel", nullable=false)
	@JsonIgnore
    private Nivel nivel;

	public Proceso() {
		
	}
	
	public Proceso(Long id, String descripcion) {
		super();
		this.id = id;
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
		return "Proceso [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
	
    

}
