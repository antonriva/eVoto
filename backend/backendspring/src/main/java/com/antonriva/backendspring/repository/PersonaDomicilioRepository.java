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
    
    // Elimina todas las relaciones de persona con domicilio dado el ID de Persona
    @Transactional
    @Modifying
    @Query("DELETE FROM PersonaDomicilio pd WHERE pd.persona.id = :personaId")
    void deleteByPersonaId(@Param("personaId") Long personaId);
    
    Optional<PersonaDomicilio> findByPersonaIdAndTipoDeDomicilioId(Long personaId, Long tipoDeDomicilioId);
    
    

}
