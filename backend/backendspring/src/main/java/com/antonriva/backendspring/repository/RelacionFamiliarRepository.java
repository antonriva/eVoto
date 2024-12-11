package com.antonriva.backendspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.antonriva.backendspring.model.RelacionFamiliar;

public interface RelacionFamiliarRepository extends JpaRepository<RelacionFamiliar, Long> {

    @Query("SELECT COUNT(rf) FROM RelacionFamiliar rf WHERE rf.padre.id = :personaId OR rf.madre.id = :personaId")
    int countHijosByPersonaId(@Param("personaId") Long personaId);
    

    @Query("SELECT rf FROM RelacionFamiliar rf WHERE rf.persona.id = :personaId")
    Optional<RelacionFamiliar> findRelacionFamiliarByPersonaId(@Param("personaId") Long personaId);
}
