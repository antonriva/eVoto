package com.antonriva.backendspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.model.Domicilio;
import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.model.PersonaDomicilio;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface DomicilioRepository extends JpaRepository<Domicilio, Integer>,  JpaSpecificationExecutor<Domicilio> {

}
