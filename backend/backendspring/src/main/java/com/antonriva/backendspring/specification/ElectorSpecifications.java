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
	
    public static Specification<Elector> conId(Long id) {
        return (root, query, criteriaBuilder) -> 
            id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }
    
    public static Specification<Elector> conIdDePersona(Long idDePersona) {
        return (root, query, criteriaBuilder) -> 
            idDePersona == null ? null : criteriaBuilder.equal(root.get("persona").get("id"), idDePersona);
    }
    
    public static Specification<Elector> conNombreDePersona(String nombre) {
        return (root, query, criteriaBuilder) -> {
            if (nombre == null || nombre.isEmpty()) return null;

            // Dividir el término de búsqueda en palabras
            String[] palabrasBusqueda = nombre.toLowerCase().split(" ");

            // Construir condiciones LIKE que aseguren que las palabras comiencen con la letra buscada
            Predicate[] predicates = Arrays.stream(palabrasBusqueda)
                    .map(palabra -> criteriaBuilder.or(
                            // Primera palabra del nombre
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get("persona").get("nombre")),
                                    palabra + "%"
                            ),
                            // Palabras después de un espacio
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get("persona").get("nombre")),
                                    "% " + palabra + "%"
                            )
                    ))
                    .toArray(Predicate[]::new);

            // Combinar las condiciones con OR
            return criteriaBuilder.or(predicates);
        };
    }

    public static Specification<Elector> conApellidoPaternoDePersona(String apellidoPaterno) {
        return (root, query, criteriaBuilder) -> {
            if (apellidoPaterno == null || apellidoPaterno.isEmpty()) return null;

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("persona").get("apellidoPaterno")),
                    "%" + apellidoPaterno.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Elector> conApellidoMaternoDePersona(String apellidoMaterno) {
        return (root, query, criteriaBuilder) -> {
            if (apellidoMaterno == null || apellidoMaterno.isEmpty()) return null;

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("persona").get("apellidoMaterno")),
                    "%" + apellidoMaterno.toLowerCase() + "%"
            );
        };
    }
    
    public static Specification<Elector> conFechaDeNacimiento(Integer anio, Integer mes, Integer dia) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Acceder a la fecha de nacimiento de la persona asociada
            Path<Date> fechaDeNacimientoPath = root.get("persona").get("fechaDeNacimiento");

            // Año
            if (anio != null && anio > 0) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, fechaDeNacimientoPath, criteriaBuilder.literal("YYYY")),
                    String.valueOf(anio)
                ));
            }

            // Mes
            if (mes != null && mes > 0) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, fechaDeNacimientoPath, criteriaBuilder.literal("MM")),
                    String.format("%02d", mes)
                ));
            }

            // Día
            if (dia != null && dia > 0) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, fechaDeNacimientoPath, criteriaBuilder.literal("DD")),
                    String.format("%02d", dia)
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Elector> conFechaDeFin(Integer anio, Integer mes, Integer dia) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Acceder a la fecha de fin de la persona asociada
            Path<Date> fechaDeFinPath = root.get("persona").get("fechaDeFin");

            // Año
            if (anio != null && anio > 0) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, fechaDeFinPath, criteriaBuilder.literal("YYYY")),
                    String.valueOf(anio)
                ));
            }

            // Mes
            if (mes != null && mes > 0) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, fechaDeFinPath, criteriaBuilder.literal("MM")),
                    String.format("%02d", mes)
                ));
            }

            // Día
            if (dia != null && dia > 0) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.function("TO_CHAR", String.class, fechaDeFinPath, criteriaBuilder.literal("DD")),
                    String.format("%02d", dia)
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Elector> conMunicipio(Long municipioId) {
        return (root, query, criteriaBuilder) -> {
            if (municipioId == null) return null;

            // Navegar de Elector -> Persona -> PersonaDomicilio -> Domicilio -> Municipio
            Join<Elector, Persona> personaJoin = root.join("persona");
            Join<Persona, PersonaDomicilio> personaDomicilioJoin = personaJoin.join("personaDomicilio", JoinType.LEFT);
            Join<PersonaDomicilio, Domicilio> domicilioJoin = personaDomicilioJoin.join("domicilio", JoinType.LEFT);
            Join<Domicilio, Municipio> municipioJoin = domicilioJoin.join("municipio", JoinType.LEFT);

            return criteriaBuilder.equal(municipioJoin.get("id"), municipioId);
        };
    }
    
    public static Specification<Elector> conEntidadFederativa(Long entidadFederativaId) {
        return (root, query, criteriaBuilder) -> {
            if (entidadFederativaId == null) return null;

            Join<Elector, Persona> personaJoin = root.join("persona");
            Join<Persona, PersonaDomicilio> personaDomicilioJoin = personaJoin.join("personaDomicilio", JoinType.LEFT);
            Join<PersonaDomicilio, Domicilio> domicilioJoin = personaDomicilioJoin.join("domicilio", JoinType.LEFT);
            Join<Domicilio, EntidadFederativa> entidadFederativaJoin = domicilioJoin.join("entidadFederativa", JoinType.LEFT);

            return criteriaBuilder.equal(entidadFederativaJoin.get("id"), entidadFederativaId);
        };
    }

    public static Specification<Elector> conLocalidad(Long localidadId) {
        return (root, query, criteriaBuilder) -> {
            if (localidadId == null) return null;

            Join<Elector, Persona> personaJoin = root.join("persona");
            Join<Persona, PersonaDomicilio> personaDomicilioJoin = personaJoin.join("personaDomicilio", JoinType.LEFT);
            Join<PersonaDomicilio, Domicilio> domicilioJoin = personaDomicilioJoin.join("domicilio", JoinType.LEFT);
            Join<Domicilio, Localidad> localidadJoin = domicilioJoin.join("localidad", JoinType.LEFT);

            return criteriaBuilder.equal(localidadJoin.get("id"), localidadId);
        };
    }
    
    public static Specification<Elector> conColonia(Long coloniaId) {
        return (root, query, criteriaBuilder) -> {
            if (coloniaId == null) return null;

            Join<Elector, Persona> personaJoin = root.join("persona");
            Join<Persona, PersonaDomicilio> personaDomicilioJoin = personaJoin.join("personaDomicilio", JoinType.LEFT);
            Join<PersonaDomicilio, Domicilio> domicilioJoin = personaDomicilioJoin.join("domicilio", JoinType.LEFT);
            Join<Domicilio, Colonia> coloniaJoin = domicilioJoin.join("colonia", JoinType.LEFT);

            return criteriaBuilder.equal(coloniaJoin.get("id"), coloniaId);
        };
    }
    
    public static Specification<Elector> conCodigoPostal(Long codigoPostalId) {
        return (root, query, criteriaBuilder) -> {
            if (codigoPostalId == null) return null;

            Join<Elector, Persona> personaJoin = root.join("persona");
            Join<Persona, PersonaDomicilio> personaDomicilioJoin = personaJoin.join("personaDomicilio", JoinType.LEFT);
            Join<PersonaDomicilio, Domicilio> domicilioJoin = personaDomicilioJoin.join("domicilio", JoinType.LEFT);
            Join<Domicilio, Postal> codigoPostalJoin = domicilioJoin.join("codigoPostal", JoinType.LEFT);

            return criteriaBuilder.equal(codigoPostalJoin.get("id"), codigoPostalId);
        };
    }
    
    public static Specification<Elector> conTipoDeDomicilio(Long tipoDeDomicilioId) {
        return (root, query, criteriaBuilder) -> {
            if (tipoDeDomicilioId == null) return null;

            Join<Elector, Persona> personaJoin = root.join("persona");
            Join<Persona, PersonaDomicilio> personaDomicilioJoin = personaJoin.join("personaDomicilio", JoinType.LEFT);
            Join<PersonaDomicilio, TipoDeDomicilio> tipoDeDomicilioJoin = personaDomicilioJoin.join("tipoDeDomicilio", JoinType.LEFT);

            return criteriaBuilder.equal(tipoDeDomicilioJoin.get("id"), tipoDeDomicilioId);
        };
    }
    
    

    

    
    
    
	
}
