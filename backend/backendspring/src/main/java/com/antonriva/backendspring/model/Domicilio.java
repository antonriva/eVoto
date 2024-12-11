package com.antonriva.backendspring.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*; //Antes estaba en javax.persistence pero ahora se encuentra en jakarta, otra dependencia


@Entity
@Table(name = "domicilio")
public class Domicilio {
	
	//Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //OneToMany
    @OneToMany(mappedBy = "domicilio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Ignorar esta relación en la serialización para evitar bucles
    private List<PersonaDomicilio> personaDomicilio;
    
    //ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddeentidad", nullable = false)
    private EntidadFederativa entidadFederativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddemunicipio", nullable = false)
    private Municipio municipio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddelocalidad", nullable = true)
    private Localidad localidad;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddecolonia", nullable = true)
    private Colonia colonia;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddepostal", nullable = true)
    private Postal postal;

    
    @Column(name = "calle", nullable = true)
    private String calle;

    @Column(name = "numext", nullable = true)
    private Integer numeroExterior;
    
    @Column(name = "numint", nullable = true)
    private Integer numeroInterior;



    public Domicilio() {
    
    }

    public Domicilio(EntidadFederativa entidadFederativa, Municipio municipio) {
    	this.entidadFederativa = entidadFederativa;
    	this.municipio = municipio;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PersonaDomicilio> getPersonaDomicilio() {
		return personaDomicilio;
	}

	public void setPersonaDomicilio(List<PersonaDomicilio> personaDomicilio) {
		this.personaDomicilio = personaDomicilio;
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

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getNumeroExterior() {
		return numeroExterior;
	}

	public void setNumeroExterior(Integer numeroExterior) {
		this.numeroExterior = numeroExterior;
	}

	public Integer getNumeroInterior() {
		return numeroInterior;
	}

	public void setNumeroInterior(Integer numeroInterior) {
		this.numeroInterior = numeroInterior;
	}

	@Override
	public String toString() {
		return "Domicilio [id=" + id + ", calle=" + calle + ", numeroExterior=" + numeroExterior + ", numeroInterior="
				+ numeroInterior + "]";
	}
    
    

}
