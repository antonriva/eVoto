package com.antonriva.backendspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.model.Postal;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface PostalRepository extends JpaRepository<Postal, Integer>,  JpaSpecificationExecutor<Postal>{

    // Buscar códigos postales por ID de colonia
    List<Postal> findByColonia_Id(int coloniaId);

}
