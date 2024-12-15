package com.antonriva.backendspring.model;

import java.time.LocalDateTime;

import com.antonriva.backendspring.id.ElectorCandidaturaId;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

public class ElectorCandidatura {
	

	//Id compuesto
    @EmbeddedId
    private ElectorCandidaturaId id;
    
    //ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDeElector") // Vincula con el campo idDePersona del EmbeddedId
    @JoinColumn(name = "iddeelector", nullable = false)
    @JsonIgnore
    private Elector elector;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDeCandidatura") 
    @JoinColumn(name = "iddecandidatura", nullable = false)
    @JsonIgnore
    private Candidatura candidatura;
    
    //Columna normal
    @Column(name = "fechahoradeinicio", nullable = false)
    private LocalDateTime fechaHoraDeInicio;

    @Column(name = "fechahoradefin", nullable = false)
    private LocalDateTime fechaHoraDeFin;

	public ElectorCandidatura( Elector elector, Candidatura candidatura,
			LocalDateTime fechaHoraDeInicio, LocalDateTime fechaHoraDeFin) {
		this.id = new ElectorCandidaturaId(elector.getId(), candidatura.getId());
		this.elector = elector;
		this.candidatura = candidatura;
		this.fechaHoraDeInicio = fechaHoraDeInicio;
		this.fechaHoraDeFin = fechaHoraDeFin;
	}

	public ElectorCandidaturaId getId() {
		return id;
	}

	public void setId(ElectorCandidaturaId id) {
		this.id = id;
	}

	public Elector getElector() {
		return elector;
	}

	public void setElector(Elector elector) {
		this.elector = elector;
	}

	public Candidatura getCandidatura() {
		return candidatura;
	}

	public void setCandidatura(Candidatura candidatura) {
		this.candidatura = candidatura;
	}

	public LocalDateTime getFechaHoraDeInicio() {
		return fechaHoraDeInicio;
	}

	public void setFechaHoraDeInicio(LocalDateTime fechaHoraDeInicio) {
		this.fechaHoraDeInicio = fechaHoraDeInicio;
	}

	public LocalDateTime getFechaHoraDeFin() {
		return fechaHoraDeFin;
	}

	public void setFechaHoraDeFin(LocalDateTime fechaHoraDeFin) {
		this.fechaHoraDeFin = fechaHoraDeFin;
	}

	@Override
	public String toString() {
		return "ElectorCandidatura [fechaHoraDeInicio=" + fechaHoraDeInicio + ", fechaHoraDeFin=" + fechaHoraDeFin
				+ "]";
	}
    
    
    

}
