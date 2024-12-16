package com.antonriva.backendspring.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.antonriva.backendspring.dto.CandidaturaElectorDetalleDTO;
import com.antonriva.backendspring.dto.CandidaturaInstanciaDetalleDTO;
import com.antonriva.backendspring.dto.ProcesoLugarDTO;
import com.antonriva.backendspring.id.PersonaDomicilioId;
import com.antonriva.backendspring.id.ElectorCandidaturaId;
import com.antonriva.backendspring.model.Candidatura;
import com.antonriva.backendspring.model.Domicilio;
import com.antonriva.backendspring.model.Elector;
import com.antonriva.backendspring.model.ElectorCandidatura;
import com.antonriva.backendspring.model.InstanciaDeProceso;
import com.antonriva.backendspring.model.Partido;
import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.model.PersonaDomicilio;
import com.antonriva.backendspring.model.ProcesoLugar;
import com.antonriva.backendspring.repository.CandidaturaRepository;
import com.antonriva.backendspring.repository.ElectorCandidaturaRepository;
import com.antonriva.backendspring.repository.ElectorRepository;
import com.antonriva.backendspring.repository.InstanciaDeProcesoRepository;
import com.antonriva.backendspring.repository.PartidoRepository;
import com.antonriva.backendspring.repository.PersonaDomicilioRepository;
import com.antonriva.backendspring.repository.ProcesoLugarRepository;
import com.antonriva.backendspring.repository.VotoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CandidaturaService {
	
	private final CandidaturaRepository candidaturaRepository;
	private final ElectorRepository electorRepository;
	private final ElectorCandidaturaRepository electorCandidaturaRepository;
	private final ProcesoLugarRepository procesoLugarRepository;
	private final PersonaDomicilioRepository personaDomicilioRepository;
	private final InstanciaDeProcesoRepository instanciaDeProcesoRepository;
	private final PartidoRepository partidoRepository;
	private final VotoRepository votoRepository;
	
	
	public CandidaturaService(CandidaturaRepository candidaturaRepository,
			ElectorRepository electorRepository, 
			ElectorCandidaturaRepository electorCandidaturaRepository, 
			ProcesoLugarRepository procesoLugarRepository,
			PersonaDomicilioRepository personaDomicilioRepository,
			InstanciaDeProcesoRepository instanciaDeProcesoRepository,
			PartidoRepository partidoRepository,
			VotoRepository votoRepository
			) {
		this.candidaturaRepository =  candidaturaRepository;
		this.electorRepository = electorRepository;
		this.electorCandidaturaRepository = electorCandidaturaRepository;
		this.procesoLugarRepository = procesoLugarRepository;
		this.personaDomicilioRepository = personaDomicilioRepository;
		this.instanciaDeProcesoRepository = instanciaDeProcesoRepository;
		this.partidoRepository = partidoRepository;
		this.votoRepository = votoRepository;
		
	}
	//Este va a ser desde la vista de candidaturaElector
	@Transactional(readOnly = true)
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

	        // Contar los votos para esta candidatura en esta instancia de proceso
	        Long votosTotales = votoRepository.countByCandidatura_IdAndInstanciaDeProceso_Id(candidatura.getId(), instanciaDeProceso.getId());
	        System.out.println("Votos totales encontrados para la candidatura ID " + candidatura.getId() + ": " + votosTotales);

	        return new CandidaturaElectorDetalleDTO(
	            candidatura.getId(),
	            instanciaDeProceso.getId(),
	            instanciaDeProceso.getNivel().getDescripcion(),
	            instanciaDeProceso.getProceso().getDescripcion(),
	            candidatura.getPartido().getDenominacion(),
	            votosTotales.intValue(), // Actualizar con el conteo de votos
	            relacion.getFechaHoraDeInicio(),
	            relacion.getFechaHoraDeFin(),
	            procesoLugar.getEntidadFederativa() != null ? procesoLugar.getEntidadFederativa().getDescripcion() : null,
	            procesoLugar.getMunicipio() != null ? procesoLugar.getMunicipio().getDescripcion() : null,
	            procesoLugar.getLocalidad() != null ? procesoLugar.getLocalidad().getDescripcion() : null
	        );
	    }).collect(Collectors.toList());
	}


    //desde la vista de instancia
    @Transactional(readOnly = true)
    public List<CandidaturaInstanciaDetalleDTO> getDetalleCandidaturaInstancia(Long idDeInstanciaDeProceso) {
        System.out.println("Buscando candidaturas asociadas a la instancia de proceso con ID: " + idDeInstanciaDeProceso);
        List<Candidatura> candidaturas = candidaturaRepository.findByInstanciaDeProcesoId(idDeInstanciaDeProceso);

        if (candidaturas.isEmpty()) {
            System.out.println("No se encontraron candidaturas para la instancia de proceso con ID: " + idDeInstanciaDeProceso);
            throw new IllegalArgumentException("No se encontraron candidaturas para la instancia de proceso proporcionada.");
        }

        return candidaturas.stream().map(candidatura -> {
            System.out.println("Procesando candidatura: " + candidatura);

            // Obtener el total de votos para esta candidatura en la instancia de proceso
            Long totalVotos = votoRepository.countByCandidatura_IdAndInstanciaDeProceso_Id(
                    candidatura.getId(), idDeInstanciaDeProceso);

            System.out.println("Total de votos para la candidatura con ID " + candidatura.getId() + ": " + totalVotos);

            Optional<ElectorCandidatura> electorCandidaturaOpt = electorCandidaturaRepository
                    .findTopByCandidaturaIdOrderByFechaHoraDeInicioAsc(candidatura.getId());

            if (electorCandidaturaOpt.isEmpty()) {
                System.out.println("No se encontró una relación ElectorCandidatura para la candidatura con ID: " + candidatura.getId());
                throw new IllegalStateException("No se encontró una relación válida en ElectorCandidatura para la candidatura con ID: " + candidatura.getId());
            }

            ElectorCandidatura electorCandidatura = electorCandidaturaOpt.get();
            System.out.println("ElectorCandidatura encontrada: " + electorCandidatura);

            Elector elector = electorCandidatura.getElector();
            Persona persona = elector.getPersona();
            System.out.println("Persona asociada: " + persona);

            Optional<PersonaDomicilio> personaDomicilioOpt = personaDomicilioRepository.findByPersonaIdAndTipoDeDomicilioId(persona.getId(), 2L);

            if (personaDomicilioOpt.isEmpty()) {
                System.out.println("No se encontró un domicilio de residencia para la persona con ID: " + persona.getId());
                throw new IllegalStateException("No se encontró un domicilio de residencia válido para la persona con ID: " + persona.getId());
            }

            PersonaDomicilio personaDomicilio = personaDomicilioOpt.get();
            Domicilio domicilioResidencia = personaDomicilio.getDomicilio();
            System.out.println("Domicilio de residencia encontrado: " + domicilioResidencia);

            return new CandidaturaInstanciaDetalleDTO(
                    candidatura.getId(),
                    candidatura.getPartido().getDenominacion(),
                    totalVotos.intValue(), // Usamos el total de votos calculado
                    electorCandidatura.getFechaHoraDeInicio(),
                    electorCandidatura.getFechaHoraDeFin(),
                    persona.getId(),
                    persona.getNombre(),
                    persona.getApellidoPaterno(),
                    persona.getApellidoMaterno(),
                    persona.getFechaDeNacimiento(),
                    persona.getFechaDeFin(),
                    domicilioResidencia.getEntidadFederativa() != null ? domicilioResidencia.getEntidadFederativa().getDescripcion() : "---",
                    domicilioResidencia.getMunicipio() != null ? domicilioResidencia.getMunicipio().getDescripcion() : "---",
                    domicilioResidencia.getLocalidad() != null ? domicilioResidencia.getLocalidad().getDescripcion() : "---"
            );
        }).collect(Collectors.toList());
    }

    
    
    //METODOS DE REGISTRO DE CANDIDATURA
    
    public Long crearCandidatura(Long idDeProcesoElectoral, Long idDePartido) {
        System.out.println("Creando candidatura para el proceso electoral con ID: " + idDeProcesoElectoral);

        Optional<InstanciaDeProceso> instanciaOpt = instanciaDeProcesoRepository.findById(idDeProcesoElectoral);
        if (instanciaOpt.isEmpty()) {
            throw new IllegalArgumentException("No se encontró la instancia de proceso con ID: " + idDeProcesoElectoral);
        }
        
        Optional<Partido> partidoOpt = partidoRepository.findById(idDePartido);
        if (partidoOpt.isEmpty()) {
            throw new IllegalArgumentException("No se encontró el partido con ID: " + idDePartido);
        }

        InstanciaDeProceso instancia = instanciaOpt.get();
        Partido partido = partidoOpt.get();
        Candidatura nuevaCandidatura = new Candidatura();
        nuevaCandidatura.setInstanciaDeProceso(instancia);
        nuevaCandidatura.setPartido(partido);
        nuevaCandidatura.setVoto(0); // Iniciar con 0 votos

        Candidatura candidaturaGuardada = candidaturaRepository.save(nuevaCandidatura);
        System.out.println("Candidatura creada con ID: " + candidaturaGuardada.getId());

        return candidaturaGuardada.getId();
    }
    
    
    
    public String crearRelacionesElectorCandidatura(Long idDeCandidatura, Long idDeElector) {
        System.out.println("Creando relación Elector-Candidatura para ID de candidatura: " + idDeCandidatura);

        Optional<Candidatura> candidaturaOpt = candidaturaRepository.findById(idDeCandidatura);
        if (candidaturaOpt.isEmpty()) {
            throw new IllegalArgumentException("No se encontró la candidatura con ID: " + idDeCandidatura);
        }

        Optional<Elector> electorOpt = electorRepository.findById(idDeElector);
        if (electorOpt.isEmpty()) {
            throw new IllegalArgumentException("No se encontró el elector con ID: " + idDeElector);
        }

        Candidatura candidatura = candidaturaOpt.get();
        Elector elector = electorOpt.get();
        InstanciaDeProceso instancia = candidatura.getInstanciaDeProceso();

        // Verificar que el elector no esté asociado a la misma instancia de proceso
        boolean existeRelacionEnInstancia = electorCandidaturaRepository
                .existsByElectorIdAndInstanciaDeProcesoId(elector.getId(), instancia.getId());

        if (existeRelacionEnInstancia) {
            throw new IllegalArgumentException("El elector ya está asociado a la instancia de proceso con ID: " + instancia.getId());
        }

        LocalDateTime fechaHoraDeFin = instancia.getFechaHoraDeFin();
        LocalDateTime fechaHoraDeInicio = LocalDateTime.now();

        if (fechaHoraDeInicio.isAfter(fechaHoraDeFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin del proceso electoral.");
        }

        ElectorCandidatura nuevaRelacion = new ElectorCandidatura();

        // Configurar el ID compuesto
        ElectorCandidaturaId idCompuesto = new ElectorCandidaturaId(elector.getId(), candidatura.getId());
        nuevaRelacion.setId(idCompuesto); // Establece el ID compuesto

        nuevaRelacion.setCandidatura(candidatura);
        nuevaRelacion.setElector(elector);
        nuevaRelacion.setFechaHoraDeInicio(fechaHoraDeInicio);
        nuevaRelacion.setFechaHoraDeFin(fechaHoraDeFin);

        electorCandidaturaRepository.save(nuevaRelacion);
        System.out.println("Relación Elector-Candidatura creada exitosamente.");

        return "Relación Elector-Candidatura creada exitosamente.";
    }
    
    public boolean existeRelacionElectorCandidatura(Long idDeCandidatura, Long idDeElector) {
        return electorCandidaturaRepository.existsByCandidaturaIdAndElectorId(idDeCandidatura, idDeElector);
    }
    
    public boolean existeCandidaturaPorInstanciaYPartido(Long idDeProcesoElectoral, Long idDePartido) {
        return candidaturaRepository.existsByInstanciaDeProcesoIdAndPartidoId(idDeProcesoElectoral, idDePartido);
    }
    
    @Transactional
    public void eliminarCandidatura(Long idCandidatura) {
        // Verificar si la candidatura existe
        Optional<Candidatura> candidaturaOpt = candidaturaRepository.findById(idCandidatura);
        if (candidaturaOpt.isEmpty()) {
            throw new EntityNotFoundException("La candidatura con ID " + idCandidatura + " no existe.");
        }

        Candidatura candidatura = candidaturaOpt.get();

        // Verificar relaciones críticas
        verificarDependenciasCriticas(candidatura);

        // Eliminar relaciones entre elector y candidatura
        electorCandidaturaRepository.deleteByCandidaturaId(idCandidatura);

        // Eliminar la candidatura
        candidaturaRepository.deleteById(idCandidatura);
        System.out.println("Candidatura con ID " + idCandidatura + " eliminada exitosamente.");
    }

    public List<String> verificarDependenciasCriticas(Candidatura candidatura) {
        List<String> dependenciasCriticas = new ArrayList<>();
        Long idCandidatura = candidatura.getId();
        InstanciaDeProceso instancia = candidatura.getInstanciaDeProceso();

        // Verificar si existen votos asociados a la candidatura
        if (votoRepository.existsByCandidaturaId(idCandidatura)) {
            dependenciasCriticas.add("Votos asociados");
        }

        // Verificar si la fecha de fin de la instancia de proceso ya concluyó
        if (instancia.getFechaHoraDeFin().isBefore(LocalDateTime.now())) {
            dependenciasCriticas.add("La instancia de proceso ya concluyó");
        }

        return dependenciasCriticas;
    }

    public Optional<Candidatura> obtenerCandidaturaPorId(Long idCandidatura) {
        System.out.println("Buscando candidatura con ID: " + idCandidatura);
        return candidaturaRepository.findById(idCandidatura);
    }
}
