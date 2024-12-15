package com.antonriva.backendspring.model;

import java.time.LocalDateTime;

import com.antonriva.backendspring.id.ElectorInstanciaId;
import com.antonriva.backendspring.id.PersonaDomicilioId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "electorinstancia")
public class ElectorInstancia {
	
	//Id compuesto por instanciaDeProceso y nivel
    @EmbeddedId
    private ElectorInstanciaId id;
    
    //ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDeInstanciaDeProceso") // Vincula con el campo idDePersona del EmbeddedId
    @JoinColumn(name = "iddeinstanciadeproceso", nullable = false)
    @JsonIgnore
    private InstanciaDeProceso instanciaDeProceso;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDeElector") 
    @JoinColumn(name = "iddeelector", nullable = false)
    @JsonIgnore
    private Elector elector;
    
    //Columna normal
    @Column(name = "fechahoradevoto", nullable = false)
    private LocalDateTime fechaHoraDeVoto;
    
    public ElectorInstancia() {}

	public ElectorInstancia(InstanciaDeProceso instanciaDeProceso, Elector elector,
			LocalDateTime fechaHoraDeVoto) {
		this.id = new ElectorInstanciaId(elector.getId(), instanciaDeProceso.getId());
		this.instanciaDeProceso = instanciaDeProceso;
		this.elector = elector;
		this.fechaHoraDeVoto = fechaHoraDeVoto;
	}

	public ElectorInstanciaId getId() {
		return id;
	}

	public void setId(ElectorInstanciaId id) {
		this.id = id;
	}

	public InstanciaDeProceso getInstanciaDeProceso() {
		return instanciaDeProceso;
	}

	public void setInstanciaDeProceso(InstanciaDeProceso instanciaDeProceso) {
		this.instanciaDeProceso = instanciaDeProceso;
	}

	public Elector getElector() {
		return elector;
	}

	public void setElector(Elector elector) {
		this.elector = elector;
	}

	public LocalDateTime getFechaHoraDeVoto() {
		return fechaHoraDeVoto;
	}

	public void setFechaHoraDeVoto(LocalDateTime fechaHoraDeVoto) {
		this.fechaHoraDeVoto = fechaHoraDeVoto;
	}

	@Override
	public String toString() {
		return "ElectorInstancia [id=" + id + ", fechaHoraDeVoto=" + fechaHoraDeVoto + "]";
	}
    
	
    

}
