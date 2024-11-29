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
@Table(name= "Postal")
public class Postal {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	@Column(name="DESCRIPCION")
    private int descripcion;
	
    @ManyToOne
    @JoinColumn(name = "iddecolonia")
    private Colonia colonia;
    
    public Postal() {
    	
    }

	public Postal(int id, int descripcion, Colonia colonia) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.colonia = colonia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(int descripcion) {
		this.descripcion = descripcion;
	}

	public Colonia getColonia() {
		return colonia;
	}

	public void setColonia(Colonia colonia) {
		this.colonia = colonia;
	}

	@Override
	public String toString() {
		return "Postal [id=" + id + ", descripcion=" + descripcion + ", colonia=" + colonia + "]";
	}
    
    

}
