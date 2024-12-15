package com.antonriva.backendspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.antonriva.backendspring.id.ElectorInstanciaId;
import com.antonriva.backendspring.model.ElectorInstancia;
import com.antonriva.backendspring.model.ProcesoLugar;

public interface ProcesoLugarRepository extends JpaRepository<ProcesoLugar, Long>, JpaSpecificationExecutor<ProcesoLugar>  {
	


}
