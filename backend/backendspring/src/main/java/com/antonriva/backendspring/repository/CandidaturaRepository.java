package com.antonriva.backendspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.model.Candidatura;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long>{
	
    boolean existsByPartidoId(Long partidoId);
    List<Candidatura> findByInstanciaDeProcesoId(Long instanciaDeProcesoId);
    
    boolean existsByInstanciaDeProcesoIdAndPartidoId(Long instanciaDeProcesoId, Long partidoId);
    
    boolean existsByInstanciaDeProcesoId(Long instanciaDeProcesoId);

}
