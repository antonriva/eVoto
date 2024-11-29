package com.antonriva.backendspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.model.Municipio;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface MunicipioRepository extends JpaRepository<Municipio, Integer>,  JpaSpecificationExecutor<Municipio>{
	
    // Buscar municipios por ID de entidad federativa
    List<Municipio> findByEntidadFederativa_Id(int entidadFederativaId);

}
