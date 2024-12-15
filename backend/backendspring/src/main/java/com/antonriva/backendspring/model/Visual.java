package com.antonriva.backendspring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name= "visual")
public class Visual {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="contenido", nullable = false)
	private String contenido;
	
    //ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddepartido", nullable = true)
    private Partido partido;
    
    //ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddetipodevisual", nullable = false)
    private TipoDeVisual tipoDeVisual;
    
    //ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idderecursovigente", nullable = false)
    private RecursoVigente recursoVigente;
    
    public Visual() {
    	
    }

	public Visual(Long id, String contenido, Partido partido) {
		super();
		this.id = id;
		this.contenido = contenido;
		this.partido = partido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	
	

	public TipoDeVisual getTipoDeVisual() {
		return tipoDeVisual;
	}

	public void setTipoDeVisual(TipoDeVisual tipoDeVisual) {
		this.tipoDeVisual = tipoDeVisual;
	}

	public RecursoVigente getRecursoVigente() {
		return recursoVigente;
	}

	public void setRecursoVigente(RecursoVigente recursoVigente) {
		this.recursoVigente = recursoVigente;
	}

	@Override
	public String toString() {
		return "Visual [id=" + id + ", contenido=" + contenido + "]";
	}
    
    
    
    

}
