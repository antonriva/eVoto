package com.antonriva.backendspring.repository;

import com.antonriva.backendspring.model.Municipio;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface MunicipioRepository extends JpaRepository<Municipio, Long>,  JpaSpecificationExecutor<Municipio>{
	
    // Buscar municipios por ID de entidad federativa
    List<Municipio> findByEntidadFederativa_Id(Long idDeEntidadFederativa);

}
