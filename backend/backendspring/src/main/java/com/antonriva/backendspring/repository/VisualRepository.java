package com.antonriva.backendspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.model.Partido;
import com.antonriva.backendspring.model.TipoDeVisual;
import com.antonriva.backendspring.model.Visual;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface VisualRepository extends JpaRepository<Visual, Long> {
	Optional<Visual> findByPartidoAndRecursoVigenteId(Partido partido, Long recursoVigenteId);
	
	Optional<Visual> findByPartidoAndRecursoVigenteIdAndTipoDeVisualId(Partido partido, Long recursoVigenteId, Long tipoDeVisualId);

	   Optional<Visual> findByPartidoIdAndTipoDeVisualIdAndRecursoVigenteId(Long partidoId, Long tipoDeVisualId, Long recursoVigenteId);

	   void deleteByPartidoId(Long partidoId);

	

}
