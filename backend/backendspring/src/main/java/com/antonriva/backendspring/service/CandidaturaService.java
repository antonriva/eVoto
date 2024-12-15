package com.antonriva.backendspring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.antonriva.backendspring.dto.CandidaturaElectorDetalleDTO;
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
	
    public List<CandidaturaElectorDetalleDTO> getDetalleDeCandidaturaElector(Long electorId) {
        System.out.println("Buscando relaciones para el elector con ID: " + electorId);
        List<ElectorCandidatura> relaciones = electorCandidaturaRepository.findByElectorId(electorId);

        if (relaciones.isEmpty()) {
            System.out.println("No se encontraron relaciones para el elector con ID: " + electorId);
            throw new IllegalArgumentException("No se encontraron relaciones para el elector con ID: " + electorId);
        }

        return relaciones.stream().map(relacion -> {
            System.out.println("Procesando relación: " + relacion);

            Candidatura candidatura = relacion.getCandidatura();
            if (candidatura == null) {
                System.out.println("La candidatura asociada no se encontró para la relación con ID de elector: " + electorId);
                throw new IllegalStateException("La candidatura asociada no se encontró para la relación con ID de elector: " + electorId);
            }
            System.out.println("Candidatura encontrada: " + candidatura);

            InstanciaDeProceso instanciaDeProceso = candidatura.getInstanciaDeProceso();
            if (instanciaDeProceso == null) {
                System.out.println("La instancia de proceso no se encontró para la candidatura con ID: " + candidatura.getId());
                throw new IllegalStateException("La instancia de proceso no se encontró para la candidatura con ID: " + candidatura.getId());
            }
            System.out.println("Instancia de proceso encontrada: " + instanciaDeProceso);

            ProcesoLugar procesoLugar = instanciaDeProceso.getProcesoLugar();
            if (procesoLugar == null) {
                System.out.println("El proceso lugar no se encontró para la instancia de proceso con ID: " + instanciaDeProceso.getId());
                throw new IllegalStateException("El proceso lugar no se encontró para la instancia de proceso con ID: " + instanciaDeProceso.getId());
            }
            System.out.println("Proceso lugar encontrado: " + procesoLugar);

            return new CandidaturaElectorDetalleDTO(
                candidatura.getId(),
                instanciaDeProceso.getId(),
                instanciaDeProceso.getNivel().getDescripcion(),
                instanciaDeProceso.getProceso().getDescripcion(),
                candidatura.getPartido().getDenominacion(),
                candidatura.getVoto(),
                relacion.getFechaHoraDeInicio(),
                relacion.getFechaHoraDeFin(),
                procesoLugar.getEntidadFederativa() != null ? procesoLugar.getEntidadFederativa().getDescripcion() : null,
                procesoLugar.getMunicipio() != null ? procesoLugar.getMunicipio().getDescripcion() : null,
                procesoLugar.getLocalidad() != null ? procesoLugar.getLocalidad().getDescripcion() : null
            );
        }).collect(Collectors.toList());
    }
}
