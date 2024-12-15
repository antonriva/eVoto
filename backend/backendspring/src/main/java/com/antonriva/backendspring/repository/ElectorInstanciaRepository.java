package com.antonriva.backendspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.id.ElectorInstanciaId;
import com.antonriva.backendspring.model.ElectorInstancia;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface ElectorInstanciaRepository extends JpaRepository<ElectorInstancia, ElectorInstanciaId>, JpaSpecificationExecutor<ElectorInstancia> {
	
	boolean existsByElectorId(Long idDeElector);


}
