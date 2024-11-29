package com.antonriva.backendspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.model.Colonia;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface ColoniaRepository extends JpaRepository<Colonia, Integer>, JpaSpecificationExecutor<Colonia> {

    // Buscar colonias por ID de municipio
    List<Colonia> findByMunicipio_Id(int municipioId);
}
