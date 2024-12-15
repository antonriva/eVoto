package com.antonriva.backendspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.model.TipoDeDomicilio;
import com.antonriva.backendspring.model.TipoDeVisual;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface TipoDeVisualRepository extends JpaRepository<TipoDeVisual, Long> {

}
