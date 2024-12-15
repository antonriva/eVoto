package com.antonriva.backendspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.antonriva.backendspring.model.Elector;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface ElectorRepository extends JpaRepository<Elector, Long>, JpaSpecificationExecutor<Elector> {

	    // Verificar si existe una relación de persona con elector
	    @Query("SELECT COUNT(e) > 0 FROM Elector e WHERE e.persona.id = :personaId")
	    boolean existsByPersonaId(@Param("personaId") Long personaId);
	
	    Optional<Elector> findByPersonaId(Long personaId);
	    

}
