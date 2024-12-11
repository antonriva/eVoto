package com.antonriva.backendspring.model;

import java.time.LocalDate;
import com.antonriva.backendspring.id.PersonaDomicilioId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*; //Antes estaba en javax.persistence pero ahora se encuentra en jakarta, otra dependencia

@Entity
@Table(name = "personadomicilio")
public class PersonaDomicilio {
	
	//Id compuesto
    @EmbeddedId
    private PersonaDomicilioId id;
    
    //ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDePersona") // Vincula con el campo idDePersona del EmbeddedId
    @JoinColumn(name = "iddepersona", nullable = false)
    @JsonIgnore
    private Persona persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDeDomicilio") 
    @JoinColumn(name = "iddedomicilio", nullable = false)
    @JsonIgnore
    private Domicilio domicilio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iddetipodedomicilio", nullable = true)
    private TipoDeDomicilio tipoDeDomicilio;
    
    //Columna normal
    @Column(name = "fechadeinicio", nullable = false)
    private LocalDate fechaDeInicio;

    @Column(name = "fechadefin", nullable = true)
    private LocalDate fechaDeFin;


    public PersonaDomicilio() {
    }

    // Constructor con fechas
    public PersonaDomicilio(Persona persona, Domicilio domicilio, LocalDate fechaDeInicio) {
        this.id = new PersonaDomicilioId(persona.getId(), domicilio.getId());
        this.persona = persona;
        this.domicilio = domicilio;
        this.fechaDeInicio = fechaDeInicio;
    }

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

	public TipoDeDomicilio getTipoDeDomicilio() {
		return tipoDeDomicilio;
	}

	public void setTipoDeDomicilio(TipoDeDomicilio tipoDeDomicilio) {
		this.tipoDeDomicilio = tipoDeDomicilio;
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
		return "PersonaDomicilio [id=" + id + ", fechaDeInicio=" + fechaDeInicio + ", fechaDeFin=" + fechaDeFin + "]";
	}

    

}


