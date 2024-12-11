package com.antonriva.backendspring.repository;


import com.antonriva.backendspring.model.Domicilio;
import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.model.PersonaDomicilio;
import com.antonriva.backendspring.id.PersonaDomicilioId;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface PersonaDomicilioRepository extends JpaRepository<PersonaDomicilio, PersonaDomicilioId>, JpaSpecificationExecutor<PersonaDomicilio> {

    Optional<PersonaDomicilio> findByPersonaAndDomicilio(Persona persona, Domicilio domicilio);

    List<PersonaDomicilio> findByPersona(Persona persona);

    List<PersonaDomicilio> findByDomicilio(Domicilio domicilio);

    void deleteByPersona(Persona persona);

    void deleteByDomicilio(Domicilio domicilio);
    
    @Query("SELECT COUNT(pd) FROM PersonaDomicilio pd WHERE pd.persona.id = :personaId")
    int countDomiciliosByPersonaId(@Param("personaId") Long personaId);
    
}
