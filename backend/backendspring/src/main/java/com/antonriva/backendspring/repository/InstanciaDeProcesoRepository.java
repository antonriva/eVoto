package com.antonriva.backendspring.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.antonriva.backendspring.model.InstanciaDeProceso;

public interface InstanciaDeProcesoRepository extends JpaRepository<InstanciaDeProceso, Integer>, JpaSpecificationExecutor<InstanciaDeProceso> {
}
