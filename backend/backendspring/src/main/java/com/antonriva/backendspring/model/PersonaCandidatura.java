package com.antonriva.backendspring.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "PersonaCandidatura")
public class PersonaCandidatura {

    @EmbeddedId
    private PersonaCandidaturaId id;  // Aquí debe ser PersonaCandidaturaId, no PersonaDomicilioId

    @ManyToOne
    @MapsId("idDePersona")  // Vincula con el campo idDePersona del EmbeddedId
    @JoinColumn(name = "iddepersona", nullable = false)
    @JsonIgnore // Evita serializar esta relación para evitar problemas de referencia circular
    private Persona persona;

    @ManyToOne
    @MapsId("idDeCandidatura")  // Vincula con el campo idDeCandidatura del EmbeddedId
    @JoinColumn(name = "iddecandidatura", nullable = false)
    private Candidatura candidatura;
    
    @Column(name = "FECHADEINICIO", nullable = false)
    private LocalDate fechaDeInicio;

    @Column(name = "FECHADEFIN")
    private LocalDate fechaDeFin;
    
    public PersonaCandidatura() {}

    public PersonaCandidatura(PersonaCandidaturaId id, Persona persona, Candidatura candidatura, LocalDate fechaDeInicio,
            LocalDate fechaDeFin) {
        this.id = id;
        this.persona = persona;
        this.candidatura = candidatura;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaDeFin = fechaDeFin;
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

    public LocalDate getFechaDeInicio() {
        return fechaDeInicio;
    }

    public void setFechaDeInicio(LocalDate fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }

    public LocalDate getFechaDeFin() {
        return fechaDeFin;
    }

    public void setFechaDeFin(LocalDate fechaDeFin) {
        this.fechaDeFin = fechaDeFin;
    }

    @Override
    public String toString() {
        return "PersonaCandidatura [id=" + id + ", persona=" + persona + ", candidatura=" + candidatura
                + ", fechaDeInicio=" + fechaDeInicio + ", fechaDeFin=" + fechaDeFin + "]";
    }
}

