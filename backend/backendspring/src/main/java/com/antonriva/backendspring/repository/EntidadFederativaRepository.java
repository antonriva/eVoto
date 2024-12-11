package com.antonriva.backendspring.repository;

import com.antonriva.backendspring.model.EntidadFederativa;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface EntidadFederativaRepository extends JpaRepository<EntidadFederativa, Long>,  JpaSpecificationExecutor<EntidadFederativa> {

}
