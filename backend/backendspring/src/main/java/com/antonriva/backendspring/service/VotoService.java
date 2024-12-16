package com.antonriva.backendspring.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.antonriva.backendspring.id.ElectorInstanciaId;
import com.antonriva.backendspring.model.Candidatura;
import com.antonriva.backendspring.model.Elector;
import com.antonriva.backendspring.model.ElectorInstancia;
import com.antonriva.backendspring.model.InstanciaDeProceso;
import com.antonriva.backendspring.model.Voto;
import com.antonriva.backendspring.repository.CandidaturaRepository;
import com.antonriva.backendspring.repository.ElectorInstanciaRepository;
import com.antonriva.backendspring.repository.ElectorRepository;
import com.antonriva.backendspring.repository.InstanciaDeProcesoRepository;
import com.antonriva.backendspring.repository.VotoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class VotoService {
	
    private final ElectorRepository electorRepository;
    private final InstanciaDeProcesoRepository instanciaDeProcesoRepository;
    private final CandidaturaRepository candidaturaRepository;
    private final VotoRepository votoRepository;
    private final ElectorInstanciaRepository electorInstanciaRepository;
    
    public VotoService(ElectorRepository electorRepository,
    		InstanciaDeProcesoRepository instanciaDeProcesoRepository,
    		CandidaturaRepository candidaturaRepository, 
    		ElectorInstanciaRepository electorInstanciaRepository,
    		VotoRepository votoRepository
    		) {
    	this.electorRepository = electorRepository;
    	this.instanciaDeProcesoRepository = instanciaDeProcesoRepository;
    	this.candidaturaRepository = candidaturaRepository;
    	this.votoRepository = votoRepository;
    	this.electorInstanciaRepository = electorInstanciaRepository;
    	
    }

	
    @Transactional
    public void registrarVoto(Long idDeElector, Long idDeInstanciaDeProceso, Long idDeCandidatura) {
        // Verificar que el elector existe
        Elector elector = electorRepository.findById(idDeElector)
                .orElseThrow(() -> new EntityNotFoundException("El elector con ID " + idDeElector + " no existe."));

        // Verificar que la instancia de proceso existe
        InstanciaDeProceso instanciaDeProceso = instanciaDeProcesoRepository.findById(idDeInstanciaDeProceso)
                .orElseThrow(() -> new EntityNotFoundException("La instancia de proceso con ID " + idDeInstanciaDeProceso + " no existe."));

        // Verificar que la candidatura existe
        Candidatura candidatura = candidaturaRepository.findById(idDeCandidatura)
                .orElseThrow(() -> new EntityNotFoundException("La candidatura con ID " + idDeCandidatura + " no existe."));

        // Verificar que la candidatura pertenece a la instancia de proceso
        if (!candidatura.getInstanciaDeProceso().getId().equals(idDeInstanciaDeProceso)) {
            throw new IllegalArgumentException("La candidatura no pertenece a la instancia de proceso proporcionada.");
        }

        // Crear y guardar el voto
        Voto voto = new Voto();
        voto.setCandidatura(candidatura);
        voto.setInstanciaDeProceso(instanciaDeProceso);
        votoRepository.save(voto);

        // Crear y guardar la relación en ElectorInstancia
        ElectorInstanciaId electorInstanciaId = new ElectorInstanciaId(idDeInstanciaDeProceso, idDeElector);

        // Verificar si ya existe una relación en ElectorInstancia para evitar duplicados
        if (electorInstanciaRepository.existsById(electorInstanciaId)) {
            throw new IllegalArgumentException("El elector ya ha votado en esta instancia de proceso.");
        }

        ElectorInstancia electorInstancia = new ElectorInstancia();
        electorInstancia.setId(electorInstanciaId);
        electorInstancia.setElector(elector);
        electorInstancia.setInstanciaDeProceso(instanciaDeProceso);
        electorInstancia.setFechaHoraDeVoto(LocalDateTime.now());
        electorInstanciaRepository.save(electorInstancia);

        // Imprimir mensaje de éxito
        System.out.println("Voto registrado exitosamente para el elector con ID " + idDeElector +
                " en la instancia de proceso con ID " + idDeInstanciaDeProceso +
                " y la candidatura con ID " + idDeCandidatura + ".");
    }

}
