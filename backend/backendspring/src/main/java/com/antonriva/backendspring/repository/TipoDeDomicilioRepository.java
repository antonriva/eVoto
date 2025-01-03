package com.antonriva.backendspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.model.TipoDeDomicilio;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface TipoDeDomicilioRepository extends JpaRepository<TipoDeDomicilio, Long> {

}
