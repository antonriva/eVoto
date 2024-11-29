package com.antonriva.backendspring.specification;

import com.antonriva.backendspring.model.Postal;
import org.springframework.data.jpa.domain.Specification;

public class PostalSpecifications {

    // Filtrar por descripción
    public static Specification<Postal> conDescripcion(Integer descripcion) {
        return (root, query, criteriaBuilder) -> {
            if (descripcion == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("descripcion"), descripcion);
        };
    }

    // Filtrar por ID de colonia
    public static Specification<Postal> conColoniaId(Integer coloniaId) {
        return (root, query, criteriaBuilder) -> {
            if (coloniaId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("colonia").get("id"), coloniaId);
        };
    }

    // Combinar filtros
    public static Specification<Postal> conFiltros(Integer descripcion, Integer coloniaId) {
        return Specification
                .where(conDescripcion(descripcion))
                .and(conColoniaId(coloniaId));
    }
}
