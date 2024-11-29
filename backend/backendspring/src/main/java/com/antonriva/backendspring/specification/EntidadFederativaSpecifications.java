package com.antonriva.backendspring.specification;

import com.antonriva.backendspring.model.EntidadFederativa;
import org.springframework.data.jpa.domain.Specification;

public class EntidadFederativaSpecifications {

    // Filtrar por descripción
    public static Specification<EntidadFederativa> conDescripcion(String descripcion) {
        return (root, query, criteriaBuilder) -> {
            if (descripcion == null || descripcion.isEmpty()) {
                return null; // No aplica filtro si la descripción es nula o vacía
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("descripcion")), "%" + descripcion.toLowerCase() + "%");
        };
    }
}
