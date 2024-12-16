package com.antonriva.backendspring.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.antonriva.backendspring.model.InstanciaDeProceso;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class InstanciaDeProcesoSpecifications {
	
    public static Specification<InstanciaDeProceso> conIdDeInstancia(Long idDeInstanciaDeProceso) {
        return (root, query, criteriaBuilder) ->
                idDeInstanciaDeProceso == null ? null : criteriaBuilder.equal(root.get("id"), idDeInstanciaDeProceso);
    }
    public static Specification<InstanciaDeProceso> conIdNivel(Long idNivel) {
        return (root, query, criteriaBuilder) -> {
            if (idNivel == null) return null;
            return criteriaBuilder.equal(root.join("nivel").get("id"), idNivel);
        };
    }

    public static Specification<InstanciaDeProceso> conIdProceso(Long idProceso) {
        return (root, query, criteriaBuilder) -> {
            if (idProceso == null) return null;
            return criteriaBuilder.equal(root.join("proceso").get("id"), idProceso);
        };
    }

    public static Specification<InstanciaDeProceso> conIdEntidadFederativa(Long idEntidadFederativa) {
        return (root, query, criteriaBuilder) -> {
            if (idEntidadFederativa == null) return null;
            return criteriaBuilder.equal(root.join("procesoLugar").join("entidadFederativa").get("id"), idEntidadFederativa);
        };
    }

    public static Specification<InstanciaDeProceso> conIdMunicipio(Long idMunicipio) {
        return (root, query, criteriaBuilder) -> {
            if (idMunicipio == null) return null;
            return criteriaBuilder.equal(root.join("procesoLugar").join("municipio").get("id"), idMunicipio);
        };
    }

    public static Specification<InstanciaDeProceso> conIdLocalidad(Long idLocalidad) {
        return (root, query, criteriaBuilder) -> {
            if (idLocalidad == null) return null;
            return criteriaBuilder.equal(root.join("procesoLugar").join("localidad").get("id"), idLocalidad);
        };
    }
    public static Specification<InstanciaDeProceso> conFechaDeFin(Integer anio, Integer mes, Integer dia) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (anio != null && anio > 0) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaHoraDeFin"), criteriaBuilder.literal("YYYY")),
                        String.valueOf(anio)
                ));
            }

            if (mes != null && mes > 0) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaHoraDeFin"), criteriaBuilder.literal("MM")),
                        String.format("%02d", mes)
                ));
            }

            if (dia != null && dia > 0) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaHoraDeFin"), criteriaBuilder.literal("DD")),
                        String.format("%02d", dia)
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
    
    public static Specification<InstanciaDeProceso> conFechaDeInicio(Integer anio, Integer mes, Integer dia) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (anio != null && anio > 0) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaHoraDeInicio"), criteriaBuilder.literal("YYYY")),
                        String.valueOf(anio)
                ));
            }

            if (mes != null && mes > 0) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaHoraDeInicio"), criteriaBuilder.literal("MM")),
                        String.format("%02d", mes)
                ));
            }

            if (dia != null && dia > 0) {
                predicates.add(criteriaBuilder.equal(
                        criteriaBuilder.function("TO_CHAR", String.class, root.get("fechaHoraDeInicio"), criteriaBuilder.literal("DD")),
                        String.format("%02d", dia)
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
	

}
