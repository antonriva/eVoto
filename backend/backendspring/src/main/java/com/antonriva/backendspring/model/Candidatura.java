package com.antonriva.backendspring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Candidatura")
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "iddepartido")
    private Partido partido;

    @ManyToOne
    @JoinColumn(name = "iddeinstancia")
    private InstanciaDeProceso instanciaDeProceso;

    @OneToOne(mappedBy = "candidatura")
    private PersonaCandidatura personaCandidatura;  // Relación con PersonaCandidatura
    
    public Candidatura() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public InstanciaDeProceso getInstanciaDeProceso() {
        return instanciaDeProceso;
    }

    public void setInstanciaDeProceso(InstanciaDeProceso instanciaDeProceso) {
        this.instanciaDeProceso = instanciaDeProceso;
    }

    @Override
    public String toString() {
        return "Candidatura [id=" + id + ", partido=" + partido + ", instanciaDeProceso=" + instanciaDeProceso + "]";
    }
}
