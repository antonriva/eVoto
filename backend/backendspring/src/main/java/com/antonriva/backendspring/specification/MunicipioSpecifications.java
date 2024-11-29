package com.antonriva.backendspring.specification;

import com.antonriva.backendspring.model.Municipio;
import org.springframework.data.jpa.domain.Specification;

public class MunicipioSpecifications {

    // Filtrar por descripción
    public static Specification<Municipio> conDescripcion(String descripcion) {
        return (root, query, criteriaBuilder) -> {
            if (descripcion == null || descripcion.isEmpty()) {
                return null; // No aplica filtro
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("descripcion")), "%" + descripcion.toLowerCase() + "%");
        };
    }

    // Filtrar por ID de entidad federativa
    public static Specification<Municipio> conEntidadFederativaId(Integer entidadFederativaId) {
        return (root, query, criteriaBuilder) -> {
            if (entidadFederativaId == null) {
                return null; // No aplica filtro
            }
            return criteriaBuilder.equal(root.get("entidadFederativa").get("id"), entidadFederativaId);
        };
    }

    // Combinar filtros
    public static Specification<Municipio> conFiltros(String descripcion, Integer entidadFederativaId) {
        return Specification
                .where(conDescripcion(descripcion))
                .and(conEntidadFederativaId(entidadFederativaId));
    }
}
