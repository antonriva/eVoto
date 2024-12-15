package com.antonriva.backendspring.specification;

import org.springframework.data.jpa.domain.Specification;

import com.antonriva.backendspring.model.InstanciaDeProceso;

public class InstanciaDeProcesoSpecifications {
	
	public static Specification<InstanciaDeProceso> conIdDeNivel(Long idDeNivel) {
	    return (root, query, criteriaBuilder) -> idDeNivel == null ? null : criteriaBuilder.equal(root.get("idDeNivel"), idDeNivel);
	   
	}

	public static Specification<InstanciaDeProceso> conIdDeProceso(Long idDeProceso) {
	    return (root, query, criteriaBuilder) -> idDeProceso == null ? null : criteriaBuilder.equal(root.get("idDeProceso"), idDeProceso);
	}

	
	

}
