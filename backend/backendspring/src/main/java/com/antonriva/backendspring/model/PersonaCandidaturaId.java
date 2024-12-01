package com.antonriva.backendspring.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PersonaCandidaturaId implements Serializable {

    @Column(name = "iddepersona")
    private int idDePersona;

    @Column(name = "iddecandidatura")
    private int idDeCandidatura;

    public PersonaCandidaturaId() {}

    public PersonaCandidaturaId(int idDePersona, int idDeCandidatura) {
        this.idDePersona = idDePersona;
        this.idDeCandidatura = idDeCandidatura;
    }

    public int getIdDePersona() {
        return idDePersona;
    }

    public void setIdDePersona(int idDePersona) {
        this.idDePersona = idDePersona;
    }

    public int getIdDeCandidatura() {
        return idDeCandidatura;
    }

    public void setIdDeCandidatura(int idDeCandidatura) {
        this.idDeCandidatura = idDeCandidatura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaCandidaturaId that = (PersonaCandidaturaId) o;
        return idDePersona == that.idDePersona && idDeCandidatura == that.idDeCandidatura;
    }

    @Override
    public int hashCode() {
        return 31 * idDePersona + idDeCandidatura;
    }
}

