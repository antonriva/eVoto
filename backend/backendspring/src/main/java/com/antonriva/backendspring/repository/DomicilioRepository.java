package com.antonriva.backendspring.repository;

import com.antonriva.backendspring.model.Domicilio;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface DomicilioRepository extends JpaRepository<Domicilio, Long>,  JpaSpecificationExecutor<Domicilio> {

}
