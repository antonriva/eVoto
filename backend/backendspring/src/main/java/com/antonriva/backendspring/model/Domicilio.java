package com.antonriva.backendspring.model;

import java.time.LocalDate;
import java.util.List;

//Antes estaba en javax.persistence pero ahora se encuentra en jakarta, otra dependencia

import jakarta.persistence.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Domicilio")
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CALLE", nullable = false)
    private String calle;

    @Column(name = "NUMEXT", nullable = false)
    private int numeroExterior;

    @Column(name = "NUMINT")
    private Integer numeroInterior;

    @ManyToOne
    @JoinColumn(name = "iddeentidad", nullable = false)
    private EntidadFederativa entidadFederativa;

    @ManyToOne
    @JoinColumn(name = "iddemunicipio", nullable = false)
    private Municipio municipio;

    @ManyToOne
    @JoinColumn(name = "iddecolonia", nullable = false)
    private Colonia colonia;

    @ManyToOne
    @JoinColumn(name = "iddepostal", nullable = false)
    private Postal postal;

    @OneToMany(mappedBy = "domicilio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonaDomicilio> personaDomicilio;

    // Constructor vacío
    public Domicilio() {
    }

    // Constructor con parámetros básicos
    public Domicilio(String calle, int numeroExterior, Integer numeroInterior) {
        this.calle = calle;
        this.numeroExterior = numeroExterior;
        this.numeroInterior = numeroInterior;
    }

    // Constructor completo
    public Domicilio(String calle, int numeroExterior, Integer numeroInterior,
                     EntidadFederativa entidadFederativa, Municipio municipio,
                     Colonia colonia, Postal postal) {
        this.calle = calle;
        this.numeroExterior = numeroExterior;
        this.numeroInterior = numeroInterior;
        this.entidadFederativa = entidadFederativa;
        this.municipio = municipio;
        this.colonia = colonia;
        this.postal = postal;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(int numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public Integer getNumeroInterior() {
        return numeroInterior;
    }

    public void setNumeroInterior(Integer numeroInterior) {
        this.numeroInterior = numeroInterior;
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

    public Colonia getColonia() {
        return colonia;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }

    public Postal getPostal() {
        return postal;
    }

    public void setPostal(Postal postal) {
        this.postal = postal;
    }

    public List<PersonaDomicilio> getPersonaDomicilio() {
        return personaDomicilio;
    }

    public void setPersonaDomicilio(List<PersonaDomicilio> personaDomicilio) {
        this.personaDomicilio = personaDomicilio;
    }

    @Override
    public String toString() {
        return "Domicilio [id=" + id + ", calle=" + calle + ", numeroExterior=" + numeroExterior +
               ", numeroInterior=" + numeroInterior + "]";
    }
}
