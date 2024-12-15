package com.antonriva.backendspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.model.Partido;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface PartidoRepository extends JpaRepository<Partido, Long>, JpaSpecificationExecutor<Partido> {
}
