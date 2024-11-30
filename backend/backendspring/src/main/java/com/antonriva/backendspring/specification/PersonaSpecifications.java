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
            return criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nombre")), pattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nombre")), "% " + pattern)
            );
        };
    }

    public static Specification<Persona> conApellidoPaterno(String apellidoPaterno) {
        return (root, query, criteriaBuilder) -> {
            if (apellidoPaterno == null || apellidoPaterno.isEmpty()) return null;

            String pattern = apellidoPaterno.toLowerCase() + "%";
            return criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("apellidoPaterno")), pattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("apellidoPaterno")), "% " + pattern)
            );
        };
    }

    public static Specification<Persona> conApellidoMaterno(String apellidoMaterno) {
        return (root, query, criteriaBuilder) -> {
            if (apellidoMaterno == null || apellidoMaterno.isEmpty()) return null;

            String pattern = apellidoMaterno.toLowerCase() + "%";
            return criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("apellidoMaterno")), pattern),
                criteriaBuilder.like(criteriaBuilder.lower(root.get("apellidoMaterno")), "% " + pattern)
            );
        };
    }

    public static Specification<Persona> conFechaDeNacimiento(String fechaDeNacimiento) {
        return (root, query, criteriaBuilder) -> {
            if (fechaDeNacimiento == null || fechaDeNacimiento.isEmpty()) return null;

            try {
                if (fechaDeNacimiento.matches("^\\d{4}$")) {
                    // Si el formato es solo año (YYYY)
                    int anio = Integer.parseInt(fechaDeNacimiento);
                    LocalDate inicioAnio = LocalDate.of(anio, 1, 1);
                    LocalDate finAnio = inicioAnio.withMonth(12).withDayOfMonth(31);
                    return criteriaBuilder.between(root.get("fechaDeNacimiento"), inicioAnio, finAnio);
                } else if (fechaDeNacimiento.matches("^\\d{4}-\\d{2}$")) {
                    // Si el formato es año y mes (YYYY-MM)
                    LocalDate inicioMes = LocalDate.parse(fechaDeNacimiento + "-01");
                    LocalDate finMes = inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());
                    return criteriaBuilder.between(root.get("fechaDeNacimiento"), inicioMes, finMes);
                } else if (fechaDeNacimiento.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                    // Si el formato es año, mes y día (YYYY-MM-DD)
                    LocalDate fechaExacta = LocalDate.parse(fechaDeNacimiento);
                    return criteriaBuilder.equal(root.get("fechaDeNacimiento"), fechaExacta);
                } else {
                    throw new IllegalArgumentException("El formato de fecha debe ser YYYY, YYYY-MM o YYYY-MM-DD");
                }
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("La fecha de nacimiento debe estar en formato válido (YYYY, YYYY-MM o YYYY-MM-DD)");
            }
        };
    }

   

}

