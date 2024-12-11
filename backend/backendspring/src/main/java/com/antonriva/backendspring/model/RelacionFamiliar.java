package com.antonriva.backendspring.model;

import jakarta.persistence.*;

@Entity
@Table(name="relacionfamiliar")
public class RelacionFamiliar {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddepersona", nullable=false)
    private Persona persona;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddepadre", nullable=true)
    private Persona padre;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddemadre", nullable=true)
    private Persona madre;
    
    public RelacionFamiliar() {
    	
    }
    
    public RelacionFamiliar(Persona persona) {
    	this.persona = persona;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Persona getPadre() {
		return padre;
	}

	public void setPadre(Persona padre) {
		this.padre = padre;
	}

	public Persona getMadre() {
		return madre;
	}

	public void setMadre(Persona madre) {
		this.madre = madre;
	}

	@Override
	public String toString() {
		return "RelacionFamiliar [id=" + id + "]";
	}
	
	
    

}
