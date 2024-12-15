package com.antonriva.backendspring.repository;

import com.antonriva.backendspring.model.Domicilio;
import com.antonriva.backendspring.model.EntidadFederativa;
import com.antonriva.backendspring.model.Municipio;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface DomicilioRepository extends JpaRepository<Domicilio, Long>,  JpaSpecificationExecutor<Domicilio> {
	Optional<Domicilio> findByEntidadFederativaIdAndMunicipioId(Long entidadFederativaId, Long municipioId);
	
    Optional<Domicilio> findByEntidadFederativaIdAndMunicipioIdAndCalleIsNullAndNumeroExteriorIsNullAndNumeroInteriorIsNullAndCodigoPostalIsNullAndColoniaIsNullAndLocalidadIsNull(
            Long entidadFederativaId, Long municipioId);

	
	
	

}
