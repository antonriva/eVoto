package com.antonriva.backendspring.model;


import com.antonriva.backendspring.id.PersonaDomicilioId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

//Esta es practicamente un instanciaDeProceso-nivel
@Entity
@Table(name = "procesolugar")
public class ProcesoLugar {
	
	//Id compuesto por instanciaDeProceso y nivel
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddenivel", nullable = true)
    private Nivel nivel;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddelocalidad", nullable = true)
    private Localidad localidad;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddeentidad", nullable = true)
    private EntidadFederativa entidadFederativa;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddemunicipio", nullable = true)
    private Municipio municipio;
    
    public ProcesoLugar() {}

	public ProcesoLugar(Long id, Nivel nivel, Localidad localidad,
			EntidadFederativa entidadFederativa, Municipio municipio) {
		this.id = id;
		this.nivel = nivel;
		this.localidad = localidad;
		this.entidadFederativa = entidadFederativa;
		this.municipio = municipio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public EntidadFederativa getEntidadFederativa() {
		return entidadFederativa;
	}

	public void setEntidadFederativa(EntidadFederativa entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@Override
	public String toString() {
		return "ProcesoLugar [id=" + id + "]";
	}
	
	
    
    

}
