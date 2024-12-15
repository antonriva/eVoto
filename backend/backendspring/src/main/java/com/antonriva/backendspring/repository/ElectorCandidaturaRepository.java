package com.antonriva.backendspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.id.ElectorCandidaturaId;
import com.antonriva.backendspring.model.ElectorCandidatura;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface ElectorCandidaturaRepository extends JpaRepository<ElectorCandidatura, ElectorCandidaturaId>, JpaSpecificationExecutor<ElectorCandidatura> {
	

}
