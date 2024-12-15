package com.antonriva.backendspring.specification;

import org.springframework.data.jpa.domain.Specification;

import com.antonriva.backendspring.model.Partido;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartidoSpecifications {
    public static Specification<Partido> conId(Long id) {
        return (root, query, criteriaBuilder) -> 
            id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Partido> conDenominacion(String denominacion) {
        return (root, query, criteriaBuilder) -> {
            if (denominacion == null || denominacion.isEmpty()) return null;

            String pattern = "%" + denominacion.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("denominacion")), pattern);
        };
    }

    public static Specification<Partido> conSiglas(String siglas) {
        return (root, query, criteriaBuilder) -> {
            if (siglas == null || siglas.isEmpty()) return null;

            String pattern = "%" + siglas.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("siglas")), pattern);
        };
    }

    public static Specification<Partido> conFechaDeInicio(Integer anio, Integer mes, Integer dia) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (anio != null && anio > 0) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeInicio"), criteriaBuilder.literal("YYYY")),
                    String.valueOf(anio)
                ));
            }

            if (mes != null && mes > 0) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeInicio"), criteriaBuilder.literal("MM")),
                    String.format("%02d", mes)
                ));
            }

            if (dia != null && dia > 0) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeInicio"), criteriaBuilder.literal("DD")),
                    String.format("%02d", dia)
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Partido> conFechaDeFin(Integer anio, Integer mes, Integer dia) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (anio != null && anio > 0) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeFin"), criteriaBuilder.literal("YYYY")),
                    String.valueOf(anio)
                ));
            }

            if (mes != null && mes > 0) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeFin"), criteriaBuilder.literal("MM")),
                    String.format("%02d", mes)
                ));
            }

            if (dia != null && dia > 0) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeFin"), criteriaBuilder.literal("DD")),
                    String.format("%02d", dia)
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
