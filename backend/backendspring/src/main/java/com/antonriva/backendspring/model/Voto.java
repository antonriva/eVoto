package com.antonriva.backendspring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "Voto")
public class Voto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @ManyToOne
    @JoinColumn(name = "iddecandidatura")
    private Candidatura candidatura;
    
    @ManyToOne
    @JoinColumn(name = "iddeinstancia")
    private InstanciaDeProceso instanciaDeProceso;
    
    public Voto() {
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Candidatura getCandidatura() {
		return candidatura;
	}

	public void setCandidatura(Candidatura candidatura) {
		this.candidatura = candidatura;
	}

	public InstanciaDeProceso getInstanciaDeProceso() {
		return instanciaDeProceso;
	}

	public void setInstanciaDeProceso(InstanciaDeProceso instanciaDeProceso) {
		this.instanciaDeProceso = instanciaDeProceso;
	}

	@Override
	public String toString() {
		return "Voto [id=" + id + ", candidatura=" + candidatura + ", instanciaDeProceso=" + instanciaDeProceso + "]";
	}

	public Voto(int id, Candidatura candidatura, InstanciaDeProceso instanciaDeProceso) {
		super();
		this.id = id;
		this.candidatura = candidatura;
		this.instanciaDeProceso = instanciaDeProceso;
	}
    
    
    
	

}
