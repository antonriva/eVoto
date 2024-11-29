package com.antonriva.backendspring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.model.Domicilio;
import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.model.PersonaDomicilio;
import com.antonriva.backendspring.model.PersonaDomicilioId;

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
    
    
}
