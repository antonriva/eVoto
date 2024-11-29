package com.antonriva.backendspring.model;

import java.time.LocalDate;

//Antes estaba en javax.persistence pero ahora se encuentra en jakarta, otra dependencia

import jakarta.persistence.*;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PersonaDomicilio")
public class PersonaDomicilio {

    @EmbeddedId
    private PersonaDomicilioId id;
    
    

    @ManyToOne
    @MapsId("idDePersona") // Vincula con el campo idDePersona del EmbeddedId
    @JoinColumn(name = "iddepersona", nullable = false)
    private Persona persona;

    @ManyToOne
    @MapsId("idDeDomicilio") // Vincula con el campo idDeDomicilio del EmbeddedId
    @JoinColumn(name = "iddedomicilio", nullable = false)
    private Domicilio domicilio;

    @Column(name = "FECHADEINICIO", nullable = false)
    private LocalDate fechaDeInicio;

    @Column(name = "FECHADEFIN")
    private LocalDate fechaDeFin;

    // Constructor vacío
    public PersonaDomicilio() {
    }

    // Constructor con fechas
    public PersonaDomicilio(Persona persona, Domicilio domicilio, LocalDate fechaDeInicio, LocalDate fechaDeFin) {
        this.id = new PersonaDomicilioId(persona.getId(), domicilio.getId());
        this.persona = persona;
        this.domicilio = domicilio;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaDeFin = fechaDeFin;
    }

    public PersonaDomicilio(Persona persona, Domicilio domicilio, LocalDate fechaDeInicio) {
        this(persona, domicilio, fechaDeInicio, null);
    }

    // Getters y Setters
    public PersonaDomicilioId getId() {
        return id;
    }

    public void setId(PersonaDomicilioId id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
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
        return "PersonaDomicilio [persona=" + persona + ", domicilio=" + domicilio + ", fechaDeInicio=" + fechaDeInicio
                + ", fechaDeFin=" + fechaDeFin + "]";
    }
}


