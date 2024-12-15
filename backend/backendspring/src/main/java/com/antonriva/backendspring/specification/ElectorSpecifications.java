package com.antonriva.backendspring.specification;

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

import jakarta.persistence.*;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElectorSpecifications {


    public static Specification<Elector> conIdElector(Long idElector) {
        return (root, query, criteriaBuilder) ->
            idElector == null ? null : criteriaBuilder.equal(root.get("id"), idElector);
    }

    public static Specification<Elector> conIdPersona(Long idPersona) {
        return (root, query, criteriaBuilder) ->
            idPersona == null ? null : criteriaBuilder.equal(root.get("persona").get("id"), idPersona);
    }

    public static Specification<Elector> conNombrePersona(String nombre) {
        return (root, query, criteriaBuilder) -> {
            if (nombre == null || nombre.isEmpty()) return null;

            String[] palabrasBusqueda = nombre.toLowerCase().split(" ");
            Predicate[] predicates = Arrays.stream(palabrasBusqueda)
                .map(palabra -> criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("persona").get("nombre")), palabra + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("persona").get("nombre")), "% " + palabra + "%")
                ))
                .toArray(Predicate[]::new);

            return criteriaBuilder.or(predicates);
        };
    }

    public static Specification<Elector> conApellidoPaternoPersona(String apellidoPaterno) {
        return (root, query, criteriaBuilder) -> {
            if (apellidoPaterno == null || apellidoPaterno.isEmpty()) return null;

            String pattern = apellidoPaterno.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("persona").get("apellidoPaterno")), pattern);
        };
    }

    public static Specification<Elector> conApellidoMaternoPersona(String apellidoMaterno) {
        return (root, query, criteriaBuilder) -> {
            if (apellidoMaterno == null || apellidoMaterno.isEmpty()) return null;

            String pattern = apellidoMaterno.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("persona").get("apellidoMaterno")), pattern);
        };
    }

    public static Specification<Elector> conFechaDeNacimientoPersona(Integer anio, Integer mes, Integer dia) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (anio != null && anio > 0) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function("TO_CHAR", String.class, root.get("persona").get("fechaDeNacimiento"), criteriaBuilder.literal("YYYY")),
                        String.valueOf(anio)
                ));
            }
            if (mes != null && mes > 0) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function("TO_CHAR", String.class, root.get("persona").get("fechaDeNacimiento"), criteriaBuilder.literal("MM")),
                        String.format("%02d", mes)
                ));
            }
            if (dia != null && dia > 0) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function("TO_CHAR", String.class, root.get("persona").get("fechaDeNacimiento"), criteriaBuilder.literal("DD")),
                        String.format("%02d", dia)
                ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Elector> conMunicipioPersona(Long municipioId) {
        return (root, query, criteriaBuilder) -> {
            if (municipioId == null) return null;

            Join<Elector, PersonaDomicilio> personaDomicilioJoin = root.join("persona").join("personaDomicilio", JoinType.LEFT);
            Join<PersonaDomicilio, Domicilio> domicilioJoin = personaDomicilioJoin.join("domicilio", JoinType.LEFT);
            Join<Domicilio, Municipio> municipioJoin = domicilioJoin.join("municipio", JoinType.LEFT);

            return criteriaBuilder.equal(municipioJoin.get("id"), municipioId);
        };
    }
    
    public static Specification<Elector> conEntidadFederativa(Long entidadFederativaId) {
        return (root, query, criteriaBuilder) -> {
            if (entidadFederativaId == null) return null;

            Join<Elector, Persona> personaJoin = root.join("persona", JoinType.LEFT);
            Join<Persona, PersonaDomicilio> personaDomicilioJoin = personaJoin.join("personaDomicilio", JoinType.LEFT);
            Join<PersonaDomicilio, Domicilio> domicilioJoin = personaDomicilioJoin.join("domicilio", JoinType.LEFT);
            Join<Domicilio, EntidadFederativa> entidadFederativaJoin = domicilioJoin.join("entidadFederativa", JoinType.LEFT);

            // Comparar por el ID de la Entidad Federativa
            return criteriaBuilder.equal(entidadFederativaJoin.get("id"), entidadFederativaId);
        };
    }

    public static Specification<Elector> conLocalidad(Long localidadId) {
        return (root, query, criteriaBuilder) -> {
            if (localidadId == null) return null;

            Join<Elector, Persona> personaJoin = root.join("persona", JoinType.LEFT);
            Join<Persona, PersonaDomicilio> personaDomicilioJoin = personaJoin.join("personaDomicilio", JoinType.LEFT);
            Join<PersonaDomicilio, Domicilio> domicilioJoin = personaDomicilioJoin.join("domicilio", JoinType.LEFT);
            Join<Domicilio, Localidad> localidadJoin = domicilioJoin.join("localidad", JoinType.LEFT);

            // Comparar por el ID de la Localidad
            return criteriaBuilder.equal(localidadJoin.get("id"), localidadId);
        };
    }

    public static Specification<Elector> conColonia(Long coloniaId) {
        return (root, query, criteriaBuilder) -> {
            if (coloniaId == null) return null;

            Join<Elector, Persona> personaJoin = root.join("persona", JoinType.LEFT);
            Join<Persona, PersonaDomicilio> personaDomicilioJoin = personaJoin.join("personaDomicilio", JoinType.LEFT);
            Join<PersonaDomicilio, Domicilio> domicilioJoin = personaDomicilioJoin.join("domicilio", JoinType.LEFT);
            Join<Domicilio, Colonia> coloniaJoin = domicilioJoin.join("colonia", JoinType.LEFT);

            // Comparar por el ID de la Colonia
            return criteriaBuilder.equal(coloniaJoin.get("id"), coloniaId);
        };
    }

    public static Specification<Elector> conCodigoPostal(Long codigoPostalId) {
        return (root, query, criteriaBuilder) -> {
            if (codigoPostalId == null) return null;

            Join<Elector, Persona> personaJoin = root.join("persona", JoinType.LEFT);
            Join<Persona, PersonaDomicilio> personaDomicilioJoin = personaJoin.join("personaDomicilio", JoinType.LEFT);
            Join<PersonaDomicilio, Domicilio> domicilioJoin = personaDomicilioJoin.join("domicilio", JoinType.LEFT);
            Join<Domicilio, Postal> codigoPostalJoin = domicilioJoin.join("codigoPostal", JoinType.LEFT);

            // Comparar por el ID de la tabla CodigoPostal
            return criteriaBuilder.equal(codigoPostalJoin.get("id"), codigoPostalId);
        };
    }

    public static Specification<Elector> conTipoDeDomicilio(Long tipoDeDomicilioId) {
        return (root, query, criteriaBuilder) -> {
            if (tipoDeDomicilioId == null) return null;

            Join<Elector, Persona> personaJoin = root.join("persona", JoinType.LEFT);
            Join<Persona, PersonaDomicilio> personaDomicilioJoin = personaJoin.join("personaDomicilio", JoinType.LEFT);
            Join<PersonaDomicilio, TipoDeDomicilio> tipoDeDomicilioJoin = personaDomicilioJoin.join("tipoDeDomicilio", JoinType.LEFT);

            // Comparar por el ID del TipoDeDomicilio
            return criteriaBuilder.equal(tipoDeDomicilioJoin.get("id"), tipoDeDomicilioId);
        };
    }

    public static Specification<Elector> conFechaDeInicioElector(Integer anio, Integer mes, Integer dia) {
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
    
    public static Specification<Elector> conFechaDeFin(Integer anio, Integer mes, Integer dia) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Agregar condición para el año si está presente
            if (anio != null && anio > 0) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeFin"), criteriaBuilder.literal("YYYY")),
                    String.valueOf(anio)
                ));
            }

            // Agregar condición para el mes si es válido
            if (mes != null && mes > 0) { // Suponemos que 0 o valores negativos indican que no hay filtro para mes
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeFin"), criteriaBuilder.literal("MM")),
                    String.format("%02d", mes)
                ));
            }

            // Agregar condición para el día si es válido
            if (dia != null && dia > 0) { // Suponemos que 0 o valores negativos indican que no hay filtro para día
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaDeFin"), criteriaBuilder.literal("DD")),
                    String.format("%02d", dia)
                ));
            }

            // Combinar todos los predicados con AND
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


    
	
}
