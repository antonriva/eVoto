package com.antonriva.backendspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.id.PersonaCandidaturaId;
import com.antonriva.backendspring.model.PersonaCandidatura;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface PersonaCandidaturaRepository extends JpaRepository<PersonaCandidatura, PersonaCandidaturaId>, JpaSpecificationExecutor<PersonaCandidatura>  {
	
    // Verificar si existe una relación de persona con candidatura
    @Query("SELECT COUNT(pc) > 0 FROM PersonaCandidatura pc WHERE pc.persona.id = :personaId")
    boolean existsByPersonaId(@Param("personaId") Long personaId);

}
