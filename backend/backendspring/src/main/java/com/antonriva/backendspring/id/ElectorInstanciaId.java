package com.antonriva.backendspring.id;

import java.io.Serializable;
import java.util.Objects;

public class ElectorInstanciaId implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
	private Long idDeInstanciaDeProceso;
    private Long idDeElector;
    
    public ElectorInstanciaId() {
    }

    
	public ElectorInstanciaId(Long idDeInstanciaDeProceso, Long idDeElector) {
		this.idDeInstanciaDeProceso = idDeInstanciaDeProceso;
		this.idDeElector = idDeElector;
	}


	public Long getIdDeInstanciaDeProceso() {
		return idDeInstanciaDeProceso;
	}


	public void setIdDeInstanciaDeProceso(Long idDeInstanciaDeProceso) {
		this.idDeInstanciaDeProceso = idDeInstanciaDeProceso;
	}


	public Long getIdDeElector() {
		return idDeElector;
	}


	public void setIdDeElector(Long idDeElector) {
		this.idDeElector = idDeElector;
	}


	@Override
	public int hashCode() {
		return Objects.hash(idDeElector, idDeInstanciaDeProceso);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElectorInstanciaId other = (ElectorInstanciaId) obj;
		return Objects.equals(idDeElector, other.idDeElector)
				&& Objects.equals(idDeInstanciaDeProceso, other.idDeInstanciaDeProceso);
	}
	
	
    
    
	

}
