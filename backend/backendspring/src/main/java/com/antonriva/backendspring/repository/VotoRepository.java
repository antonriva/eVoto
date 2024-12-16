package com.antonriva.backendspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.model.Voto;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface VotoRepository extends JpaRepository<Voto, Long>, JpaSpecificationExecutor<Voto>  {
	boolean existsByCandidaturaId(Long candidaturaId);
	Long countByInstanciaDeProcesoId(Long instanciaDeProcesoId);
	
    boolean existsByInstanciaDeProcesoId(Long instanciaDeProcesoId);
    
    Long countByCandidatura_IdAndInstanciaDeProceso_Id(Long idDeCandidatura, Long idDeInstanciaDeProceso);
    
	

}
