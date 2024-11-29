package com.antonriva.backendspring.specification;

import com.antonriva.backendspring.model.Colonia;
import org.springframework.data.jpa.domain.Specification;

public class ColoniaSpecifications {

    // Filtrar por descripción
    public static Specification<Colonia> conDescripcion(String descripcion) {
        return (root, query, criteriaBuilder) -> {
            if (descripcion == null || descripcion.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("descripcion")), "%" + descripcion.toLowerCase() + "%");
        };
    }

    // Filtrar por ID de municipio
    public static Specification<Colonia> conMunicipioId(Integer municipioId) {
        return (root, query, criteriaBuilder) -> {
            if (municipioId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("municipio").get("id"), municipioId);
        };
    }

    // Combinar filtros
    public static Specification<Colonia> conFiltros(String descripcion, Integer municipioId) {
        return Specification
                .where(conDescripcion(descripcion))
                .and(conMunicipioId(municipioId));
    }
}
