package com.antonriva.backendspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.model.Candidatura;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long>{
	
    boolean existsByPartidoId(Long partidoId);

}
