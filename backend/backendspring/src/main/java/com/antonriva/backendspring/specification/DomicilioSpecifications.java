package com.antonriva.backendspring.specification;
import com.antonriva.backendspring.model.Domicilio;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class DomicilioSpecifications {

    public static Specification<Domicilio> conCalle(String calle) {
        return (root, query, criteriaBuilder) -> {
            if (calle == null || calle.isEmpty()) return null;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("calle")), calle.toLowerCase() + "%");
        };
    }

    public static Specification<Domicilio> conNumeroExterior(Integer numeroExterior) {
        return (root, query, criteriaBuilder) -> {
            if (numeroExterior == null) return null;
            return criteriaBuilder.equal(root.get("numeroExterior"), numeroExterior);
        };
    }

    public static Specification<Domicilio> conMunicipio(String municipio) {
        return (root, query, criteriaBuilder) -> {
            if (municipio == null || municipio.isEmpty()) return null;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("municipio").get("nombre")), municipio.toLowerCase() + "%");
        };
    }

    public static Specification<Domicilio> conColonia(String colonia) {
        return (root, query, criteriaBuilder) -> {
            if (colonia == null || colonia.isEmpty()) return null;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("colonia").get("nombre")), colonia.toLowerCase() + "%");
        };
    }

    public static Specification<Domicilio> conFiltros(String calle, Integer numeroExterior, String municipio, String colonia) {
        return Specification
                .where(conCalle(calle))
                .and(conNumeroExterior(numeroExterior))
                .and(conMunicipio(municipio))
                .and(conColonia(colonia));
    }
}
