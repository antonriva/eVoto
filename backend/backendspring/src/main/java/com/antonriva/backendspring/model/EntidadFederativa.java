package com.antonriva.backendspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "EntidadFederativa")
public class EntidadFederativa {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	@Column(name="DESCRIPCION")
    private String descripcion;
	
	public EntidadFederativa() {
		
	}
    
	public EntidadFederativa(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
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

	@Override
	public String toString() {
		return "EntidadFederativa [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
	

}
