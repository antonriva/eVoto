package com.antonriva.backendspring.repository;

import com.antonriva.backendspring.model.Persona;
import java.time.LocalDate;
import java.util.Optional;

//Imports necesarios para el repositorio
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface PersonaRepository extends JpaRepository<Persona, Long>,  JpaSpecificationExecutor<Persona> {
	
    // Método para verificar si existe una persona con un nombre y fecha de nacimiento específicos
    boolean existsByNombreAndFechaDeNacimiento(String nombre, LocalDate fechaDeNacimiento);
    
    // METODO QUE BUSCA UNA PERSONA EXACTAMENTE CON UN DOMICILIO EXACTO 
    @Query("""
    	    SELECT p 
    	    FROM Persona p
    	    JOIN PersonaDomicilio pd ON pd.persona = p
    	    JOIN Domicilio d ON pd.domicilio = d
    	    WHERE p.nombre = :nombre
    	      AND p.apellidoPaterno = :apellidoPaterno
    	      AND p.apellidoMaterno = :apellidoMaterno
    	      AND p.fechaDeNacimiento = :fechaDeNacimiento
    	      AND pd.tipoDeDomicilio.id = 1
    	      AND d.entidadFederativa.id = :entidadFederativaId
    	      AND d.municipio.id = :municipioId
    	      AND d.localidad IS NULL
    	      AND d.colonia IS NULL
    	      AND d.codigoPostal IS NULL
    	      AND d.calle IS NULL
    	      AND d.numeroExterior IS NULL
    	      AND d.numeroInterior IS NULL
    	""")
    	Optional<Persona> findPersonaExacta(
    	        @Param("nombre") String nombre,
    	        @Param("apellidoPaterno") String apellidoPaterno,
    	        @Param("apellidoMaterno") String apellidoMaterno,
    	        @Param("fechaDeNacimiento") LocalDate fechaDeNacimiento,
    	        @Param("entidadFederativaId") Long entidadFederativaId,
    	        @Param("municipioId") Long municipioId
    	);



}


