package com.antonriva.backendspring.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.antonriva.backendspring.model.InstanciaDeProceso;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface InstanciaDeProcesoRepository extends JpaRepository<InstanciaDeProceso, Long>, JpaSpecificationExecutor<InstanciaDeProceso>  {
	
    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END " +
            "FROM InstanciaDeProceso i " +
            "JOIN i.procesoLugar pl " +
            "WHERE (:entidadFederativaId IS NULL OR pl.entidadFederativa.id = :entidadFederativaId) " +
            "AND (:municipioId IS NULL OR pl.municipio.id = :municipioId) " +
            "AND (:localidadId IS NULL OR pl.localidad.id = :localidadId) " +
            "AND i.nivel.id = :nivelId " +
            "AND i.proceso.id = :procesoId")
     boolean existsByEntidadMunicipioLocalidadNivelProceso(
             @Param("entidadFederativaId") Long entidadFederativaId,
             @Param("municipioId") Long municipioId,
             @Param("localidadId") Long localidadId,
             @Param("nivelId") Long nivelId,
             @Param("procesoId") Long procesoId
     );
    

    List<InstanciaDeProceso> findAllByFechaHoraDeInicioBeforeAndFechaHoraDeFinAfter(LocalDateTime inicio, LocalDateTime fin);
    

}
