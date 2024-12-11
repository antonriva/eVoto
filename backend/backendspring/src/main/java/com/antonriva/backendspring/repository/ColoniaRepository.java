package com.antonriva.backendspring.repository;

import com.antonriva.backendspring.model.Colonia;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface ColoniaRepository extends JpaRepository<Colonia, Long>, JpaSpecificationExecutor<Colonia> {

    // Buscar colonias por ID de municipio
    List<Colonia> findByMunicipio_Id(Long idDeMunicipio);
}
