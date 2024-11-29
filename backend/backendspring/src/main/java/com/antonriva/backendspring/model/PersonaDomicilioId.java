package com.antonriva.backendspring.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PersonaDomicilioId implements Serializable {

    private Integer idDePersona;
    private Integer idDeDomicilio;

    // Constructor vacío
    public PersonaDomicilioId() {
    }

    // Constructor con parámetros
    public PersonaDomicilioId(Integer idDePersona, Integer idDeDomicilio) {
        this.idDePersona = idDePersona;
        this.idDeDomicilio = idDeDomicilio;
    }

    // Getters y Setters
    public Integer getIdDePersona() {
        return idDePersona;
    }

    public void setIdDePersona(Integer idDePersona) {
        this.idDePersona = idDePersona;
    }

    public Integer getIdDeDomicilio() {
        return idDeDomicilio;
    }

    public void setIdDeDomicilio(Integer idDeDomicilio) {
        this.idDeDomicilio = idDeDomicilio;
    }

    // Métodos equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaDomicilioId that = (PersonaDomicilioId) o;
        return Objects.equals(idDePersona, that.idDePersona) &&
               Objects.equals(idDeDomicilio, that.idDeDomicilio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDePersona, idDeDomicilio);
    }
}

