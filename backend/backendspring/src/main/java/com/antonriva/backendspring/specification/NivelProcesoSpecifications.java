package com.antonriva.backendspring.specification;

import org.springframework.data.jpa.domain.Specification;

import com.antonriva.backendspring.model.NivelProceso;

public class NivelProcesoSpecifications {
    public static Specification<NivelProceso> conIdDeNivel(Integer idDeNivel) {
        return (root, query, criteriaBuilder) -> idDeNivel == null ? null : criteriaBuilder.equal(root.get("nivel").get("id"), idDeNivel);
    }

    public static Specification<NivelProceso> conIdDeProceso(Integer idDeProceso) {
        return (root, query, criteriaBuilder) -> idDeProceso == null ? null : criteriaBuilder.equal(root.get("proceso").get("id"), idDeProceso);
    }

    public static Specification<NivelProceso> conDescripcionNivel(String descripcion) {
        return (root, query, criteriaBuilder) -> descripcion == null ? null : criteriaBuilder.like(root.get("nivel").get("descripcion"), "%" + descripcion + "%");
    }

    public static Specification<NivelProceso> conNombreProceso(String nombre) {
        return (root, query, criteriaBuilder) -> nombre == null ? null : criteriaBuilder.like(root.get("proceso").get("nombre"), "%" + nombre + "%");
    }
}
