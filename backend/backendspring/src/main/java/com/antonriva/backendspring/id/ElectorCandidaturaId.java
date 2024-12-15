package com.antonriva.backendspring.id;

import java.io.Serializable;
import java.util.Objects;

public class ElectorCandidaturaId implements Serializable{
	

    private static final long serialVersionUID = 1L;
    
	private Long idDeElector;
    private Long idDeCandidatura;
    
    public ElectorCandidaturaId() {
    }

    public ElectorCandidaturaId(Long idDeElector, Long idDeCandidatura) {
        this.idDeElector = idDeElector;
        this.idDeCandidatura = idDeCandidatura;
    }


	public Long getIdDeElector() {
		return idDeElector;
	}

	public void setIdDeElector(Long idDeElector) {
		this.idDeElector = idDeElector;
	}

	public Long getIdDeCandidatura() {
		return idDeCandidatura;
	}

	public void setIdDeCandidatura(Long idDeCandidatura) {
		this.idDeCandidatura = idDeCandidatura;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idDeCandidatura, idDeElector);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElectorCandidaturaId other = (ElectorCandidaturaId) obj;
		return Objects.equals(idDeCandidatura, other.idDeCandidatura) && Objects.equals(idDeElector, other.idDeElector);
	}

	@Override
	public String toString() {
		return "ElectorCandidaturaId [idDeElector=" + idDeElector + ", idDeCandidatura=" + idDeCandidatura + "]";
	}
	
	
	
	

}
