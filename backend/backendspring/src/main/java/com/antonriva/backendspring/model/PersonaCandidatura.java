package com.antonriva.backendspring.model;

import java.time.LocalDateTime;

import com.antonriva.backendspring.id.PersonaCandidaturaId;
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
@Table(name= "personacandidatura")
public class PersonaCandidatura {
	
	//Id compuesto
    @EmbeddedId
    private PersonaCandidaturaId id;
    
    //ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDePersona") // Vincula con el campo idDePersona del EmbeddedId
    @JoinColumn(name = "iddepersona", nullable = false)
    @JsonIgnore
    private Persona persona;

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
    
    public PersonaCandidatura() {
    }

    // Constructor con fechas
    public PersonaCandidatura(Persona persona, Candidatura candidatura, LocalDateTime fechaHoraDeInicio, LocalDateTime fechaHoraDeFin) {
        this.id = new PersonaCandidaturaId(persona.getId(), candidatura.getId());
        this.persona = persona;
        this.candidatura = candidatura;
        this.fechaHoraDeInicio = fechaHoraDeInicio;
        this.fechaHoraDeFin = fechaHoraDeFin;
    }

	public PersonaCandidaturaId getId() {
		return id;
	}

	public void setId(PersonaCandidaturaId id) {
		this.id = id;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
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
		return "PersonaCandidatura [id=" + id + ", fechaHoraDeInicio=" + fechaHoraDeInicio + ", fechaHoraDeFin="
				+ fechaHoraDeFin + "]";
	}


}
