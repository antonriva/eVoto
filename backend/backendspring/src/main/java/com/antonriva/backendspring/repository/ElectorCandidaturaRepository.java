package com.antonriva.backendspring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.id.ElectorCandidaturaId;
import com.antonriva.backendspring.model.ElectorCandidatura;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface ElectorCandidaturaRepository extends JpaRepository<ElectorCandidatura, ElectorCandidaturaId>, JpaSpecificationExecutor<ElectorCandidatura> {
	
	boolean existsByElectorId(Long idDeElector);

    // Buscar todas las relaciones de ElectorCandidatura por ID de Elector
    List<ElectorCandidatura> findByElectorId(Long idDeElector);
	
    List<ElectorCandidatura> findById_IdDeElector(Long idDeElector);
    
    // Método para encontrar la relación más antigua entre un elector y una candidatura
    Optional<ElectorCandidatura> findTopByCandidaturaIdOrderByFechaHoraDeInicioAsc(Long candidaturaId);
    
    boolean existsByCandidaturaIdAndElectorId(Long candidaturaId, Long electorId);
    
    // Verifica si existe una relación entre un elector y una instancia de proceso específica a través de la candidatura
    @Query("SELECT CASE WHEN COUNT(ec) > 0 THEN TRUE ELSE FALSE END " +
           "FROM ElectorCandidatura ec " +
           "JOIN ec.candidatura c " +
           "WHERE ec.elector.id = :electorId AND c.instanciaDeProceso.id = :instanciaDeProcesoId")
    boolean existsByElectorIdAndInstanciaDeProcesoId(@Param("electorId") Long electorId, 
                                                     @Param("instanciaDeProcesoId") Long instanciaDeProcesoId);

    void deleteByCandidaturaId(Long candidaturaId);
}
