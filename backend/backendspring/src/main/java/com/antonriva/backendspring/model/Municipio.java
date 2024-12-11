package com.antonriva.backendspring.model;

import jakarta.persistence.*;

@Entity
@Table(name= "Municipio")
public class Municipio {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddeentidad")
    private EntidadFederativa entidadFederativa;
    
	@Column(name="descripcion", nullable=false)
    private String descripcion;
	
    
    public Municipio() {
    	
    }

	public Municipio(String descripcion, EntidadFederativa entidadFederativa) {
		this.descripcion = descripcion;
		this.entidadFederativa = entidadFederativa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntidadFederativa getEntidadFederativa() {
		return entidadFederativa;
	}

	public void setEntidadFederativa(EntidadFederativa entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Municipio [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
	
    
}
