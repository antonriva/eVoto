package com.antonriva.backendspring.specification;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.antonriva.backendspring.model.Colonia;
import com.antonriva.backendspring.model.Domicilio;
import com.antonriva.backendspring.model.Elector;
import com.antonriva.backendspring.model.EntidadFederativa;
import com.antonriva.backendspring.model.Localidad;
import com.antonriva.backendspring.model.Municipio;
import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.model.PersonaDomicilio;
import com.antonriva.backendspring.model.Postal;
import com.antonriva.backendspring.model.TipoDeDomicilio;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class PersonaSpecifications {

    public static Specification<Persona> conId(Long id) {
        return (root, query, criteriaBuilder) -> 
            id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

//    public static Specification<Persona> conNombre(String nombre) {
//        return (root, query, criteriaBuilder) -> {
//            if (nombre == null || nombre.isEmpty()) return null;
//
//            // Dividir el término de búsqueda en palabras
//            String[] palabrasBusqueda = nombre.toLowerCase().split(" ");
//
//            // Construir condiciones LIKE que aseguren que las palabras comiencen con la letra buscada
//            Predicate[] predicates = Arrays.stream(palabrasBusqueda)
//                    .map(palabra -> criteriaBuilder.or(
//                            // Primera palabra del nombre
//                            criteriaBuilder.like(
//                                    criteriaBuilder.lower(root.get("nombre")),
//                                    palabra + "%"
//                            ),
//                            // Palabras después de un espacio
//                            criteriaBuilder.like(
//                                    criteriaBuilder.lower(root.get("nombre")),
//                                    "% " + palabra + "%"
//                            )
//                    ))
//                    .toArray(Predicate[]::new);
//
//            // Combinar las condiciones con OR
//            return criteriaBuilder.or(predicates);
//        };
//    }
    
    public static Specification<Persona> conNombre(String nombre) {
        return (root, query, cb) -> {
            if (nombre == null || nombre.trim().isEmpty()) return null;

            String[] palabras = nombre.toLowerCase().trim().split("\\s+");

            // Caso: búsqueda progresiva
            List<Predicate> predicates = new ArrayList<>();
            for (String palabra : palabras) {
                predicates.add(cb.like(
                    cb.lower(root.get("nombre")),
                    "%" + palabra + "%"
                ));
            }

            // Si se escribió todo el nombre completo, exigir que empiece exactamente con esa frase
            if (palabras.length > 1) {
                return cb.like(
                    cb.lower(root.get("nombre")),
                    nombre.toLowerCase() + "%"
                );
            }

            // Si solo es una palabra, combinar por OR parcial
            return cb.and(predicates.toArray(new Predicate[0]));
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
    

    public static Specification<Persona> conFechaDeNacimiento(Integer anio, Integer mes, Integer dia) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Agregar condición para el año si está presente
            if (anio != null && anio > 0) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeNacimiento"), criteriaBuilder.literal("YYYY")),
                    String.valueOf(anio)
                ));
            }

            // Agregar condición para el mes si es válido
            if (mes != null && mes > 0) { // Suponemos que 0 o valores negativos indican que no hay filtro para mes
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeNacimiento"), criteriaBuilder.literal("MM")),
                    String.format("%02d", mes)
                ));
            }

            // Agregar condición para el día si es válido
            if (dia != null && dia > 0) { // Suponemos que 0 o valores negativos indican que no hay filtro para día
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeNacimiento"), criteriaBuilder.literal("DD")),
                    String.format("%02d", dia)
                ));
            }

            // Combinar todos los predicados con AND
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }



    // Similar modification for conFechaDeFin

    public static Specification<Persona> conFechaDeFin(Integer anio, Integer mes, Integer dia) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Agregar condición para el año si está presente
            if (anio != null && anio > 0) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeNacimiento"), criteriaBuilder.literal("YYYY")),
                    String.valueOf(anio)
                ));
            }

            // Agregar condición para el mes si es válido
            if (mes != null && mes > 0) { // Suponemos que 0 o valores negativos indican que no hay filtro para mes
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeNacimiento"), criteriaBuilder.literal("MM")),
                    String.format("%02d", mes)
                ));
            }

            // Agregar condición para el día si es válido
            if (dia != null && dia > 0) { // Suponemos que 0 o valores negativos indican que no hay filtro para día
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeNacimiento"), criteriaBuilder.literal("DD")),
                    String.format("%02d", dia)
                ));
            }

            // Combinar todos los predicados con AND
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    //
    //
    //
    //
    //
    //
    //
    public static Specification<Persona> conMunicipio(Long municipioId) {
        return (root, query, criteriaBuilder) -> {
            if (municipioId == null) return null;

            // Join con la tabla intermedia PersonaDomicilio
            Join<Persona, PersonaDomicilio> personaDomicilioJoin = root.join("personaDomicilio", JoinType.LEFT);

            // Join con Domicilio
            Join<PersonaDomicilio, Domicilio> domicilioJoin = personaDomicilioJoin.join("domicilio", JoinType.LEFT);

            // Join con Municipio dentro de Domicilio
            Join<Domicilio, Municipio> municipioJoin = domicilioJoin.join("municipio", JoinType.LEFT);

            // Comparar por ID del municipio
            return criteriaBuilder.equal(municipioJoin.get("id"), municipioId);
        };
    }



    public static Specification<Persona> conEntidadFederativa(Long entidadFederativaId) {
        return (root, query, criteriaBuilder) -> {
            if (entidadFederativaId == null) return null;

            Join<Persona, PersonaDomicilio> personaDomicilioJoin = root.join("personaDomicilio", JoinType.LEFT);
            Join<PersonaDomicilio, Domicilio> domicilioJoin = personaDomicilioJoin.join("domicilio", JoinType.LEFT);
            Join<Domicilio, EntidadFederativa> entidadFederativaJoin = domicilioJoin.join("entidadFederativa", JoinType.LEFT);

            // Comparar por el ID de la Entidad Federativa
            return criteriaBuilder.equal(entidadFederativaJoin.get("id"), entidadFederativaId);
        };
    }

    public static Specification<Persona> conLocalidad(Long localidadId) {
        return (root, query, criteriaBuilder) -> {
            if (localidadId == null) return null;

            Join<Persona, PersonaDomicilio> personaDomicilioJoin = root.join("personaDomicilio", JoinType.LEFT);
            Join<PersonaDomicilio, Domicilio> domicilioJoin = personaDomicilioJoin.join("domicilio", JoinType.LEFT);
            Join<Domicilio, Localidad> localidadJoin = domicilioJoin.join("localidad", JoinType.LEFT);

            // Comparar por el ID de la Localidad
            return criteriaBuilder.equal(localidadJoin.get("id"), localidadId);
        };
    }



    public static Specification<Persona> conColonia(Long coloniaId) {
        return (root, query, criteriaBuilder) -> {
            if (coloniaId == null) return null;

            Join<Persona, PersonaDomicilio> personaDomicilioJoin = root.join("personaDomicilio", JoinType.LEFT);
            Join<PersonaDomicilio, Domicilio> domicilioJoin = personaDomicilioJoin.join("domicilio", JoinType.LEFT);
            Join<Domicilio, Colonia> coloniaJoin = domicilioJoin.join("colonia", JoinType.LEFT);

            // Comparar por el ID de la Colonia
            return criteriaBuilder.equal(coloniaJoin.get("id"), coloniaId);
        };
    }



    public static Specification<Persona> conCodigoPostal(Long codigoPostalId) {
        return (root, query, criteriaBuilder) -> {
            if (codigoPostalId == null) return null;

            // Join con la tabla intermedia PersonaDomicilio
            Join<Persona, PersonaDomicilio> personaDomicilioJoin = root.join("personaDomicilio", JoinType.LEFT);

            // Join con Domicilio
            Join<PersonaDomicilio, Domicilio> domicilioJoin = personaDomicilioJoin.join("domicilio", JoinType.LEFT);

            // Join con la tabla CodigoPostal
            Join<Domicilio, Postal> codigoPostalJoin = domicilioJoin.join("codigoPostal", JoinType.LEFT);

            // Comparar por el ID de la tabla CodigoPostal
            return criteriaBuilder.equal(codigoPostalJoin.get("id"), codigoPostalId);
        };
    }
    
    public static Specification<Persona> conTipoDeDomicilio(Long tipoDeDomicilioId) {
        return (root, query, criteriaBuilder) -> {
            if (tipoDeDomicilioId == null) return null;

            // Join con la tabla intermedia PersonaDomicilio
            Join<Persona, PersonaDomicilio> personaDomicilioJoin = root.join("personaDomicilio", JoinType.LEFT);

            // Join con TipoDeDomicilio
            Join<PersonaDomicilio, TipoDeDomicilio> tipoDeDomicilioJoin = personaDomicilioJoin.join("tipoDeDomicilio", JoinType.LEFT);

            // Filtro por el ID del TipoDeDomicilio
            return criteriaBuilder.equal(tipoDeDomicilioJoin.get("id"), tipoDeDomicilioId);
        };
    }
    




 
}