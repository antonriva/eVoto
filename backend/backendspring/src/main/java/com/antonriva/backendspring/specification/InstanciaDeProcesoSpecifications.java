package com.antonriva.backendspring.specification;

import org.springframework.data.jpa.domain.Specification;

import com.antonriva.backendspring.model.InstanciaDeProceso;

import java.time.LocalDate;

public class InstanciaDeProcesoSpecifications {
    public static Specification<InstanciaDeProceso> conFechaDeInicio(LocalDate fechaDeInicio) {
        return (root, query, criteriaBuilder) -> fechaDeInicio == null ? null : criteriaBuilder.equal(root.get("fechaDeInicio"), fechaDeInicio);
    }

    public static Specification<InstanciaDeProceso> conFechaDeFin(LocalDate fechaDeFin) {
        return (root, query, criteriaBuilder) -> fechaDeFin == null ? null : criteriaBuilder.equal(root.get("fechaDeFin"), fechaDeFin);
    }

    public static Specification<InstanciaDeProceso> conGanadoresNum(Integer ganadoresNum) {
        return (root, query, criteriaBuilder) -> ganadoresNum == null ? null : criteriaBuilder.equal(root.get("ganadoresNum"), ganadoresNum);
    }
}
