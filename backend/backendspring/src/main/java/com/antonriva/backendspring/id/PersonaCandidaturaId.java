package com.antonriva.backendspring.id;

import java.io.Serializable;
import java.util.Objects;

public class PersonaCandidaturaId implements Serializable {

    private static final long serialVersionUID = 1L;
    
	private Long idDePersona;
    private Long idDeCandidatura;
    
    public PersonaCandidaturaId() {
    }

    public PersonaCandidaturaId(Long idDePersona, Long idDeCandidatura) {
        this.idDePersona = idDePersona;
        this.idDeCandidatura = idDeCandidatura;
    }

	public Long getIdDePersona() {
		return idDePersona;
	}

	public void setIdDePersona(Long idDePersona) {
		this.idDePersona = idDePersona;
	}

	public Long getIdDeCandidatura() {
		return idDeCandidatura;
	}

	public void setIdDeCandidatura(Long idDeCandidatura) {
		this.idDeCandidatura = idDeCandidatura;
	}
    
    // Métodos equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonaCandidaturaId that = (PersonaCandidaturaId) o;
        return Objects.equals(idDePersona, that.idDePersona) &&
               Objects.equals(idDeCandidatura, that.idDeCandidatura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDePersona, idDeCandidatura);
    }

	@Override
	public String toString() {
		return "PersonaCandidaturaId [idDePersona=" + idDePersona + ", idDeCandidatura=" + idDeCandidatura + "]";
	}
    


}
