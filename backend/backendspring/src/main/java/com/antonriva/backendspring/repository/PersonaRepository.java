package com.antonriva.backendspring.repository;

import com.antonriva.backendspring.model.Persona;
//Imports necesarios para el repositorio
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//Revisar si no es con javax
import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
	

}
