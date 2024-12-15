package com.antonriva.backendspring.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.antonriva.backendspring.dto.CandidaturaDetalleDTO;
import com.antonriva.backendspring.dto.ProcesoLugarDTO;
import com.antonriva.backendspring.model.Candidatura;
import com.antonriva.backendspring.model.Elector;
import com.antonriva.backendspring.model.ElectorCandidatura;
import com.antonriva.backendspring.model.InstanciaDeProceso;
import com.antonriva.backendspring.model.ProcesoLugar;
import com.antonriva.backendspring.repository.CandidaturaRepository;
import com.antonriva.backendspring.repository.ElectorCandidaturaRepository;
import com.antonriva.backendspring.repository.ElectorRepository;
import com.antonriva.backendspring.repository.ProcesoLugarRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CandidaturaService {
	
	private final CandidaturaRepository candidaturaRepository;
	private final ElectorRepository electorRepository;
	private final ElectorCandidaturaRepository electorCandidaturaRepository;
	private final ProcesoLugarRepository procesoLugarRepository;
	
	public CandidaturaService(CandidaturaRepository candidaturaRepository,
			ElectorRepository electorRepository, 
			ElectorCandidaturaRepository electorCandidaturaRepository, 
			ProcesoLugarRepository procesoLugarRepository
			) {
		this.candidaturaRepository =  candidaturaRepository;
		this.electorRepository = electorRepository;
		this.electorCandidaturaRepository = electorCandidaturaRepository;
		this.procesoLugarRepository = procesoLugarRepository;
		
	}
	
	@Transactional(readOnly = true)
	public List<CandidaturaDetalleDTO> obtenerCandidaturasPorElector(Long idDeElector) {
	    System.out.println("Iniciando búsqueda de candidaturas para el elector con ID: " + idDeElector);

	    // Verificar si el elector existe
	    Elector elector = electorRepository.findById(idDeElector)
	            .orElseThrow(() -> {
	                System.err.println("El elector con ID " + idDeElector + " no existe.");
	                return new EntityNotFoundException("El elector con ID " + idDeElector + " no existe.");
	            });
	    System.out.println("Elector encontrado: ID " + elector.getId());

	    // Obtener todas las relaciones ElectorCandidatura para el elector
	    List<ElectorCandidatura> relaciones = electorCandidaturaRepository.findByElectorId(idDeElector);
	    System.out.println("Relaciones ElectorCandidatura encontradas: " + relaciones.size());

	    // Transformar las relaciones en DTOs con la información requerida
	    return relaciones.stream().map(relacion -> {
	        System.out.println("Procesando relación ElectorCandidatura con ID: " + relacion.getId());

	        // Inicializar relación perezosa
	        Hibernate.initialize(relacion.getCandidatura());
	        Candidatura candidatura = relacion.getCandidatura();
	        System.out.println("Candidatura encontrada: ID " + candidatura.getId());

	        Hibernate.initialize(candidatura.getInstanciaDeProceso());
	        InstanciaDeProceso instanciaDeProceso = candidatura.getInstanciaDeProceso();
	        System.out.println("Instancia de Proceso asociada: ID " + instanciaDeProceso.getId());

	        // Asegurar que ProcesoLugar está cargado
	        ProcesoLugar procesoLugar = procesoLugarRepository.findByInstanciaDeProcesoId(instanciaDeProceso.getId())
	                .orElseThrow(() -> {
	                    System.err.println("No se encontró ProcesoLugar asociado a la instancia de proceso con ID " + instanciaDeProceso.getId());
	                    return new EntityNotFoundException(
	                            "No se encontró ProcesoLugar asociado a la instancia de proceso con ID " + instanciaDeProceso.getId());
	                });
	        System.out.println("ProcesoLugar encontrado para InstanciaDeProceso ID: " + instanciaDeProceso.getId());

	        // Transformar ProcesoLugar a DTO
	        ProcesoLugarDTO lugarDTO = new ProcesoLugarDTO(
	                procesoLugar.getMunicipio() != null ? procesoLugar.getMunicipio().getDescripcion() : null,
	                procesoLugar.getEntidadFederativa() != null ? procesoLugar.getEntidadFederativa().getDescripcion() : null,
	                procesoLugar.getLocalidad() != null ? procesoLugar.getLocalidad().getDescripcion() : null
	        );
	        System.out.println("ProcesoLugarDTO creado: " + lugarDTO);

	        // Crear el DTO principal
	        CandidaturaDetalleDTO candidaturaDetalleDTO = new CandidaturaDetalleDTO(
	                candidatura.getId(),
	                instanciaDeProceso.getId(),
	                instanciaDeProceso.getNivel().getDescripcion(),
	                instanciaDeProceso.getProceso().getDescripcion(),
	                candidatura.getPartido().getDenominacion(),
	                candidatura.getVoto(),
	                relacion.getFechaHoraDeInicio(),
	                relacion.getFechaHoraDeFin(),
	                lugarDTO
	        );
	        System.out.println("CandidaturaDetalleDTO creado: " + candidaturaDetalleDTO);

	        return candidaturaDetalleDTO;
	    }).toList();
	}



}
