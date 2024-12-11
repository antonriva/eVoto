package com.antonriva.backendspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.antonriva.backendspring.model.Localidad;

public interface LocalidadRepository extends JpaRepository<Localidad, Long>, JpaSpecificationExecutor<Localidad> {
	
    // Buscar colonias por ID de municipio
    List<Localidad> findByMunicipio_Id(Long idDeMunicipio);

}
