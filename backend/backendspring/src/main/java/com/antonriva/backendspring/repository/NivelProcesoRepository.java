package com.antonriva.backendspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.antonriva.backendspring.model.NivelProceso;

public interface NivelProcesoRepository extends JpaRepository<NivelProceso, Integer>, JpaSpecificationExecutor<NivelProceso> {
}
