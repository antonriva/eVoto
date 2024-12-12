package com.antonriva.backendspring.specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import com.antonriva.backendspring.model.Persona;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class PersonaSpecifications {

    public static Specification<Persona> conId(Long id) {
        return (root, query, criteriaBuilder) -> 
            id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Persona> conNombre(String nombre) {
        return (root, query, criteriaBuilder) -> {
            if (nombre == null || nombre.isEmpty()) return null;

            // Dividir el término de búsqueda en palabras
            String[] palabrasBusqueda = nombre.toLowerCase().split(" ");

            // Construir condiciones LIKE que aseguren que las palabras comiencen con la letra buscada
            Predicate[] predicates = Arrays.stream(palabrasBusqueda)
                    .map(palabra -> criteriaBuilder.or(
                            // Primera palabra del nombre
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get("nombre")),
                                    palabra + "%"
                            ),
                            // Palabras después de un espacio
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get("nombre")),
                                    "% " + palabra + "%"
                            )
                    ))
                    .toArray(Predicate[]::new);

            // Combinar las condiciones con OR
            return criteriaBuilder.or(predicates);
        };
    }


    public static Specification<Persona> conApellidoPaterno(String apellidoPaterno) {
        return (root, query, criteriaBuilder) -> {
            if (apellidoPaterno == null || apellidoPaterno.isEmpty()) return null;

            String pattern = apellidoPaterno.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("apellidoPaterno")), pattern);
        };
    }

    public static Specification<Persona> conApellidoMaterno(String apellidoMaterno) {
        return (root, query, criteriaBuilder) -> {
            if (apellidoMaterno == null || apellidoMaterno.isEmpty()) return null;

            String pattern = apellidoMaterno.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("apellidoMaterno")), pattern);
        };
    }
    
/*
    public static Specification<Persona> conFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        return (root, query, criteriaBuilder) -> {
            // Si la fecha es null, no aplica filtro
            if (fechaDeNacimiento == null) return null;

            // Generar el filtro usando LocalDate directamente
            return criteriaBuilder.equal(root.get("fechaDeNacimiento"), fechaDeNacimiento);
        };
    }

 
    public static Specification<Persona> conFechaDeFin(LocalDate fechaDeFin) {
        return (root, query, criteriaBuilder) -> {
            // Si la fecha es null, no aplica filtro
            if (fechaDeFin == null) return null;

            // Generar el filtro usando LocalDate directamente
            return criteriaBuilder.equal(root.get("fechaDeFin"), fechaDeFin);
        };
    }
    */
    public static Specification<Persona> conFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        return (root, query, criteriaBuilder) -> {
            if (fechaDeNacimiento == null) return null;

            List<Predicate> predicates = new ArrayList<>();

            // Agregar condición para el año
            predicates.add(criteriaBuilder.equal(
                criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeNacimiento"), criteriaBuilder.literal("YYYY")),
                String.valueOf(fechaDeNacimiento.getYear())
            ));

            // Agregar condición para el mes si es necesario
            if (fechaDeNacimiento.getMonthValue() != 1) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeNacimiento"), criteriaBuilder.literal("MM")),
                    String.format("%02d", fechaDeNacimiento.getMonthValue())
                ));
            }

            // Agregar condición para el día si es necesario
            if (fechaDeNacimiento.getDayOfMonth() != 1) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeNacimiento"), criteriaBuilder.literal("DD")),
                    String.format("%02d", fechaDeNacimiento.getDayOfMonth())
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


    // Similar modification for conFechaDeFin

    public static Specification<Persona> conFechaDeFin(LocalDate fechaDeFin) {
        return (root, query, criteriaBuilder) -> {
            if (fechaDeFin == null) return null;

            List<Predicate> predicates = new ArrayList<>();

            // Agregar condición para el año
            predicates.add(criteriaBuilder.equal(
                criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeFin"), criteriaBuilder.literal("YYYY")),
                String.valueOf(fechaDeFin.getYear())
            ));

            // Agregar condición para el mes si es necesario
            if (fechaDeFin.getMonthValue() != 1) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeFin"), criteriaBuilder.literal("MM")),
                    String.format("%02d", fechaDeFin.getMonthValue())
                ));
            }

            // Agregar condición para el día si es necesario
            if (fechaDeFin.getDayOfMonth() != 1) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeFin"), criteriaBuilder.literal("DD")),
                    String.format("%02d", fechaDeFin.getDayOfMonth())
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


    // Similar modification for conFechaDeFin

    public static Specification<Persona> conCantidadHijos(int cantidadHijos) {
        return (root, query, criteriaBuilder) -> {
            if (cantidadHijos < 0) return null;
            query.groupBy(root.get("id"));
            return criteriaBuilder.equal(criteriaBuilder.count(root.join("relacionFamiliar", JoinType.LEFT)), cantidadHijos);
        };
    }

    public static Specification<Persona> conCantidadDomicilios(int cantidadDomicilios) {
        return (root, query, criteriaBuilder) -> {
            if (cantidadDomicilios < 0) return null;
            query.groupBy(root.get("id"));
            return criteriaBuilder.equal(criteriaBuilder.count(root.join("personaDomicilio", JoinType.LEFT)), cantidadDomicilios);
        };
    }
}