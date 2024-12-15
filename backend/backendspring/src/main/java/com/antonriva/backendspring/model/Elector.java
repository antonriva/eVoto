package com.antonriva.backendspring.model;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name= "elector")
public class Elector {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddepersona", nullable = false)
    @JsonIgnore
    private Persona persona;
    
	@Column(name="fechadeinicio", nullable = false)
	private LocalDate fechaDeInicio;

	@Column(name="fechadefin", nullable = true)
	private LocalDate fechaDeFin;


    public Elector() {
    	
    }
    
	public Elector(Long id, Persona persona) {
		this.id = id;
		this.persona = persona;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "Elector [id=" + id + "]";
	}
    
}
