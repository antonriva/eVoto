package com.antonriva.backendspring.specification;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.data.jpa.domain.Specification;
import com.antonriva.backendspring.model.Persona;

public class PersonaSpecifications {

    public static Specification<Persona> conId(Integer id) {
        return (root, query, criteriaBuilder) -> 
            id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Persona> conNombre(String nombre) {
        return (root, query, criteriaBuilder) -> {
            if (nombre == null || nombre.isEmpty()) return null;

            String pattern = nombre.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("nombre")), pattern);
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

    public static Specification<Persona> conFechaDeNacimiento(String fechaDeNacimiento) {
        return (root, query, criteriaBuilder) -> {
            if (fechaDeNacimiento == null || fechaDeNacimiento.isEmpty()) return null;

            try {
                LocalDate fecha = LocalDate.parse(fechaDeNacimiento);
                return criteriaBuilder.equal(root.get("fechaDeNacimiento"), fecha);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("La fecha de nacimiento debe estar en formato válido (YYYY-MM-DD)");
            }
        };
    }

    public static Specification<Persona> conFechaDeFin(String fechaDeFin) {
        return (root, query, criteriaBuilder) -> {
            if (fechaDeFin == null || fechaDeFin.isEmpty()) return null;

            try {
                LocalDate fecha = LocalDate.parse(fechaDeFin);
                return criteriaBuilder.equal(root.get("fechaDeFin"), fecha);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("La fecha de fin debe estar en formato válido (YYYY-MM-DD)");
            }
        };
    }
}