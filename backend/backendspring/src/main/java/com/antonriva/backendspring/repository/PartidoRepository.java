package com.antonriva.backendspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.model.EntidadFederativa;
import com.antonriva.backendspring.model.Partido;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface PartidoRepository extends JpaRepository<Partido, Long>, JpaSpecificationExecutor<Partido> {
	 Optional<Partido> findByDenominacionOrSiglas(String denominacion, String siglas);
	 Partido findById(long id); // Devuelve directamente la entidad
}
