package com.antonriva.backendspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "Municipio")
public class Municipio {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	@Column(name="DESCRIPCION")
    private String descripcion;
	
    @ManyToOne
    @JoinColumn(name = "iddeentidad")
    private EntidadFederativa entidadFederativa;
    
    public Municipio() {
    	
    }

	public Municipio(int id, String descripcion, EntidadFederativa entidadFederativa) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.entidadFederativa = entidadFederativa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EntidadFederativa getEntidadFederativa() {
		return entidadFederativa;
	}

	public void setEntidadFederativa(EntidadFederativa entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}

	@Override
	public String toString() {
		return "Municipio [id=" + id + ", descripcion=" + descripcion + ", entidadFederativa=" + entidadFederativa
				+ "]";
	}
    
    
}
