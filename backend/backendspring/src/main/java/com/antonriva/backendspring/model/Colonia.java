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
@Table(name= "Colonia")
public class Colonia {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	@Column(name="DESCRIPCION")
    private String descripcion;
	
    @ManyToOne
    @JoinColumn(name = "iddemunicipio")
    private Municipio municipio;
    
    public Colonia() {
    	
    }

	public Colonia(int id, String descripcion, Municipio municipio) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.municipio = municipio;
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

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@Override
	public String toString() {
		return "Colonia [id=" + id + ", descripcion=" + descripcion + ", municipio=" + municipio + "]";
	}
    
    

}
