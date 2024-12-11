package com.antonriva.backendspring.id;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class PersonaDomicilioId implements Serializable {

    private static final long serialVersionUID = 1L;
    
	private Long idDePersona;
    private Long idDeDomicilio;

    public PersonaDomicilioId() {
    }

    public PersonaDomicilioId(Long idDePersona, Long idDeDomicilio) {
        this.idDePersona = idDePersona;
        this.idDeDomicilio = idDeDomicilio;
    }

    public Long getIdDePersona() {
        return idDePersona;
    }

    public void setIdDePersona(Long idDePersona) {
        this.idDePersona = idDePersona;
    }

    public Long getIdDeDomicilio() {
        return idDeDomicilio;
    }

    public void setIdDeDomicilio(Long idDeDomicilio) {
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

	@Override
	public String toString() {
		return "PersonaDomicilioId [idDePersona=" + idDePersona + ", idDeDomicilio=" + idDeDomicilio + "]";
	}
}

