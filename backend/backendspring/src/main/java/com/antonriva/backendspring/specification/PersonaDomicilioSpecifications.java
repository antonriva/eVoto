package com.antonriva.backendspring.specification;

import com.antonriva.backendspring.model.PersonaDomicilio;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PersonaDomicilioSpecifications {

    // Filtrar por Persona ID
    public static Specification<PersonaDomicilio> conPersonaId(Integer personaId) {
        return (root, query, criteriaBuilder) -> {
            if (personaId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("persona").get("id"), personaId);
        };
    }

    // Filtrar por Domicilio ID
    public static Specification<PersonaDomicilio> conDomicilioId(Integer domicilioId) {
        return (root, query, criteriaBuilder) -> {
            if (domicilioId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("domicilio").get("id"), domicilioId);
        };
    }

    // Filtrar por Fecha de Inicio
    public static Specification<PersonaDomicilio> conFechaDeInicio(LocalDate fechaDeInicio) {
        return (root, query, criteriaBuilder) -> {
            if (fechaDeInicio == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("fechaDeInicio"), fechaDeInicio);
        };
    }

    // Filtrar por Fecha de Fin
    public static Specification<PersonaDomicilio> conFechaDeFin(LocalDate fechaDeFin) {
        return (root, query, criteriaBuilder) -> {
            if (fechaDeFin == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("fechaDeFin"), fechaDeFin);
        };
    }
}
