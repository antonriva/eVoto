package com.antonriva.backendspring.repository;

import com.antonriva.backendspring.model.Persona;

import java.time.LocalDate;

//Imports necesarios para el repositorio
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//Revisar si no es con javax
import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface PersonaRepository extends JpaRepository<Persona, Integer>,  JpaSpecificationExecutor<Persona> {
	
    // Método para verificar si existe una persona con un nombre y fecha de nacimiento específicos
    boolean existsByNombreAndFechaDeNacimiento(String nombre, LocalDate fechaDeNacimiento);
}


