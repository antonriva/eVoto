package com.antonriva.backendspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "candidatura")
public class Candidatura {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddeinstanciadeproceso", nullable = false)
    private InstanciaDeProceso instanciaDeProceso;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddepartido", nullable = false)
    private Partido partido;
    
    @Column(name = "voto", nullable = true)
    private Integer voto;
    
    public Candidatura() {
    	
    }

	public Candidatura(Long id, InstanciaDeProceso instanciaDeProceso, Partido partido) {
		super();
		this.id = id;
		this.instanciaDeProceso = instanciaDeProceso;
		this.partido = partido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InstanciaDeProceso getInstanciaDeProceso() {
		return instanciaDeProceso;
	}

	public void setInstanciaDeProceso(InstanciaDeProceso instanciaDeProceso) {
		this.instanciaDeProceso = instanciaDeProceso;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Integer getVoto() {
		return voto;
	}

	public void setVoto(Integer voto) {
		this.voto = voto;
	}

	@Override
	public String toString() {
		return "Candidatura [id=" + id + ", voto=" + voto + "]";
	}

	

}
