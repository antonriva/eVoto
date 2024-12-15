package com.antonriva.backendspring.id;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProcesoLugarId implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
	private Long idDeInstanciaDeProceso;
    private Long idDeNivel;

    public ProcesoLugarId() {
    }

    public ProcesoLugarId(Long idDeInstanciaDeProceso, Long idDeNivel) {
        this.idDeInstanciaDeProceso =  idDeInstanciaDeProceso;
        this.idDeNivel = idDeNivel;
    }

	public Long getIdDeInstanciaDeProceso() {
		return idDeInstanciaDeProceso;
	}

	public void setIdDeInstanciaDeProceso(Long idDeInstanciaDeProceso) {
		this.idDeInstanciaDeProceso = idDeInstanciaDeProceso;
	}

	public Long getIdDeNivel() {
		return idDeNivel;
	}

	public void setIdDeNivel(Long idDeNivel) {
		this.idDeNivel = idDeNivel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idDeInstanciaDeProceso, idDeNivel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcesoLugarId other = (ProcesoLugarId) obj;
		return Objects.equals(idDeInstanciaDeProceso, other.idDeInstanciaDeProceso)
				&& Objects.equals(idDeNivel, other.idDeNivel);
	}

	@Override
	public String toString() {
		return "ProcesoLugarId [idDeInstanciaDeProceso=" + idDeInstanciaDeProceso + ", idDeNivel=" + idDeNivel + "]";
	}
    
    

}
