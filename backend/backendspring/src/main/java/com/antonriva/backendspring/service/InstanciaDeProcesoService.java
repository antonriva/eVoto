package com.antonriva.backendspring.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.antonriva.backendspring.dto.InstanciaDeProcesoDetalleDTO;
import com.antonriva.backendspring.dto.InstanciaEditarDTO;
import com.antonriva.backendspring.model.EntidadFederativa;
import com.antonriva.backendspring.model.InstanciaDeProceso;
import com.antonriva.backendspring.model.Localidad;
import com.antonriva.backendspring.model.Municipio;
import com.antonriva.backendspring.model.Nivel;
import com.antonriva.backendspring.model.Proceso;
import com.antonriva.backendspring.model.ProcesoLugar;
import com.antonriva.backendspring.repository.CandidaturaRepository;
import com.antonriva.backendspring.repository.ColoniaRepository;
import com.antonriva.backendspring.repository.EntidadFederativaRepository;
import com.antonriva.backendspring.repository.InstanciaDeProcesoRepository;
import com.antonriva.backendspring.repository.LocalidadRepository;
import com.antonriva.backendspring.repository.MunicipioRepository;
import com.antonriva.backendspring.repository.NivelRepository;
import com.antonriva.backendspring.repository.ProcesoLugarRepository;
import com.antonriva.backendspring.repository.ProcesoRepository;
import com.antonriva.backendspring.repository.VotoRepository;
import com.antonriva.backendspring.specification.InstanciaDeProcesoSpecifications;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InstanciaDeProcesoService {
	
    
    private final InstanciaDeProcesoRepository instanciaDeProcesoRepository;
    private final VotoRepository votoRepository;
    private final EntidadFederativaRepository entidadFederativaRepository;
    private final MunicipioRepository municipioRepository;
    private final LocalidadRepository localidadRepository;
    private final NivelRepository nivelRepository;
    private final ProcesoRepository procesoRepository;
    private final ProcesoLugarRepository procesoLugarRepository;
    private final CandidaturaRepository candidaturaRepository;
    
    public InstanciaDeProcesoService(InstanciaDeProcesoRepository instanciaDeProcesoRepository, 
    		VotoRepository votoRepository, 
    		EntidadFederativaRepository entidadFederativaRepository,
    		MunicipioRepository municipioRepository,
    		LocalidadRepository localidadRepository,
    		NivelRepository nivelRepository,
    		ProcesoRepository procesoRepository,
    		ProcesoLugarRepository procesoLugarRepository,
    		CandidaturaRepository candidaturaRepository
    		) {
    	this.instanciaDeProcesoRepository = instanciaDeProcesoRepository;
    	this.votoRepository = votoRepository;	
    	this.entidadFederativaRepository=entidadFederativaRepository;
    	this.municipioRepository = municipioRepository;
    	this.localidadRepository = localidadRepository;
    	this.procesoRepository = procesoRepository;
    	this.nivelRepository = nivelRepository;
    	this.procesoLugarRepository = procesoLugarRepository;
    	this.candidaturaRepository = candidaturaRepository;
    }
	
    
    
    //buscar con filtros
    public List<InstanciaDeProcesoDetalleDTO> buscarInstanciasConFiltros(
            Long idDeInstanciaDeProceso,
            Long idNivel,
            Long idProceso,
            Long idEntidadFederativa,
            Long idMunicipio,
            Long idLocalidad,
            Integer anioInicio, Integer mesInicio, Integer diaInicio,
            Integer anioFin, Integer mesFin, Integer diaFin
    ) {
        Specification<InstanciaDeProceso> spec = Specification.where(InstanciaDeProcesoSpecifications.conIdDeInstancia(idDeInstanciaDeProceso))
                .and(InstanciaDeProcesoSpecifications.conIdNivel(idNivel))
                .and(InstanciaDeProcesoSpecifications.conIdProceso(idProceso))
                .and(InstanciaDeProcesoSpecifications.conIdEntidadFederativa(idEntidadFederativa))
                .and(InstanciaDeProcesoSpecifications.conIdMunicipio(idMunicipio))
                .and(InstanciaDeProcesoSpecifications.conIdLocalidad(idLocalidad))
                .and(InstanciaDeProcesoSpecifications.conFechaDeInicio(anioInicio, mesInicio, diaInicio))
                .and(InstanciaDeProcesoSpecifications.conFechaDeFin(anioFin, mesFin, diaFin));

        return instanciaDeProcesoRepository.findAll(spec).stream().map(instancia -> {
            Long votoTotal = votoRepository.countByInstanciaDeProcesoId(instancia.getId());
            String entidadFederativaDescripcion = instancia.getProcesoLugar().getEntidadFederativa() != null
                    ? instancia.getProcesoLugar().getEntidadFederativa().getDescripcion()
                    : "---";
            String municipioDescripcion = instancia.getProcesoLugar().getMunicipio() != null
                    ? instancia.getProcesoLugar().getMunicipio().getDescripcion()
                    : "---";
            String localidadDescripcion = instancia.getProcesoLugar().getLocalidad() != null
                    ? instancia.getProcesoLugar().getLocalidad().getDescripcion()
                    : "---";

            return new InstanciaDeProcesoDetalleDTO(
                    instancia.getId(),
                    instancia.getNivel().getDescripcion(), // Obtener descripción para el DTO
                    instancia.getProceso().getDescripcion(), // Obtener descripción para el DTO
                    entidadFederativaDescripcion, // Manejar nulos con "---"
                    municipioDescripcion, // Manejar nulos con "---"
                    localidadDescripcion, // Manejar nulos con "---"
                    votoTotal,
                    instancia.getFechaHoraDeInicio(),
                    instancia.getFechaHoraDeFin(),
                    instancia.getGanadoresNum()
            );
        }).collect(Collectors.toList());
    }
    
    
    
    
    ///editar
    
    @Transactional(readOnly = true)
    public InstanciaEditarDTO obtenerDatosParaEdicion(Long idDeInstancia) {
        // Buscar la instancia de proceso
        InstanciaDeProceso instancia = instanciaDeProcesoRepository.findById(idDeInstancia)
                .orElseThrow(() -> new EntityNotFoundException("La instancia con ID " + idDeInstancia + " no existe."));

        // Obtener el ProcesoLugar asociado
        ProcesoLugar procesoLugar = instancia.getProcesoLugar();

        // Crear y retornar el DTO
        return new InstanciaEditarDTO(
                instancia.getNivel().getId(),
                instancia.getProceso().getId(),
                instancia.getFechaHoraDeInicio(),
                instancia.getFechaHoraDeFin(),
                instancia.getGanadoresNum(),
                procesoLugar.getEntidadFederativa() != null ? procesoLugar.getEntidadFederativa().getId() : null,
                procesoLugar.getMunicipio() != null ? procesoLugar.getMunicipio().getId() : null,
                procesoLugar.getLocalidad() != null ? procesoLugar.getLocalidad().getId() : null
        );
    }
   
    
    @Transactional
    public void editarInstancia(Long idDeInstancia, InstanciaEditarDTO dto) {
        // Buscar la instancia de proceso
        InstanciaDeProceso instancia = instanciaDeProcesoRepository.findById(idDeInstancia)
                .orElseThrow(() -> new EntityNotFoundException("La instancia con ID " + idDeInstancia + " no existe."));

        // Actualizar valores de InstanciaDeProceso
        if (dto.getIdDeNivel() != null) {
            Nivel nivel = nivelRepository.findById(dto.getIdDeNivel())
                    .orElseThrow(() -> new EntityNotFoundException("El nivel con ID " + dto.getIdDeNivel() + " no existe."));
            instancia.setNivel(nivel);
        }

        if (dto.getIdDeProceso() != null) {
            Proceso proceso = procesoRepository.findById(dto.getIdDeProceso())
                    .orElseThrow(() -> new EntityNotFoundException("El proceso con ID " + dto.getIdDeProceso() + " no existe."));
            instancia.setProceso(proceso);
        }

        if (dto.getFechaHoraDeInicio() != null) {
            instancia.setFechaHoraDeInicio(dto.getFechaHoraDeInicio());
        }

        if (dto.getFechaHoraDeFin() != null) {
            instancia.setFechaHoraDeFin(dto.getFechaHoraDeFin());
        }

        if (dto.getGanadoresNum() != null) {
            instancia.setGanadoresNum(dto.getGanadoresNum());
        }

        // Actualizar valores de ProcesoLugar
        ProcesoLugar procesoLugar = instancia.getProcesoLugar();

        if (dto.getIdDeEntidadFederativa() != null) {
            EntidadFederativa entidadFederativa = entidadFederativaRepository.findById(dto.getIdDeEntidadFederativa())
                    .orElseThrow(() -> new EntityNotFoundException("La entidad federativa con ID " + dto.getIdDeEntidadFederativa() + " no existe."));
            procesoLugar.setEntidadFederativa(entidadFederativa);
        }

        if (dto.getIdDeMunicipio() != null) {
            Municipio municipio = municipioRepository.findById(dto.getIdDeMunicipio())
                    .orElseThrow(() -> new EntityNotFoundException("El municipio con ID " + dto.getIdDeMunicipio() + " no existe."));
            procesoLugar.setMunicipio(municipio);
        }

        if (dto.getIdDeLocalidad() != null) {
            Localidad localidad = localidadRepository.findById(dto.getIdDeLocalidad())
                    .orElseThrow(() -> new EntityNotFoundException("La localidad con ID " + dto.getIdDeLocalidad() + " no existe."));
            procesoLugar.setLocalidad(localidad);
        }

        // Guardar los cambios
        instanciaDeProcesoRepository.save(instancia);
        System.out.println("Instancia de proceso actualizada correctamente.");
    }

    
    
    //crear nueva 
    
    
    @Transactional
    public Long registrarNuevaInstancia(InstanciaEditarDTO dto) {
        // Verificar si ya existe una instancia con los mismos valores exactos
        boolean existeInstancia = instanciaDeProcesoRepository.existsByEntidadMunicipioLocalidadNivelProceso(
                dto.getIdDeEntidadFederativa(),
                dto.getIdDeMunicipio(),
                dto.getIdDeLocalidad(),
                dto.getIdDeNivel(),
                dto.getIdDeProceso()
        );

        if (existeInstancia) {
            throw new IllegalArgumentException("Ya existe una instancia de proceso con los valores proporcionados.");
        }

        // Crear el ProcesoLugar
        ProcesoLugar procesoLugar = new ProcesoLugar();
        
        if (dto.getIdDeNivel() != null) {
            Nivel nivel = nivelRepository.findById(dto.getIdDeNivel())
                    .orElseThrow(() -> new EntityNotFoundException("El nivel con ID " + dto.getIdDeNivel() + " no existe."));
            procesoLugar.setNivel(nivel);
        }
        
        if (dto.getIdDeEntidadFederativa() != null) {
            EntidadFederativa entidadFederativa = entidadFederativaRepository.findById(dto.getIdDeEntidadFederativa())
                    .orElseThrow(() -> new EntityNotFoundException("La entidad federativa con ID " + dto.getIdDeEntidadFederativa() + " no existe."));
            procesoLugar.setEntidadFederativa(entidadFederativa);
        }

        if (dto.getIdDeMunicipio() != null) {
            Municipio municipio = municipioRepository.findById(dto.getIdDeMunicipio())
                    .orElseThrow(() -> new EntityNotFoundException("El municipio con ID " + dto.getIdDeMunicipio() + " no existe."));
            procesoLugar.setMunicipio(municipio);
        }

        if (dto.getIdDeLocalidad() != null) {
            Localidad localidad = localidadRepository.findById(dto.getIdDeLocalidad())
                    .orElseThrow(() -> new EntityNotFoundException("La localidad con ID " + dto.getIdDeLocalidad() + " no existe."));
            procesoLugar.setLocalidad(localidad);
        }

        ProcesoLugar procesoLugarGuardado = procesoLugarRepository.save(procesoLugar);

        // Crear la InstanciaDeProceso
        InstanciaDeProceso instanciaDeProceso = new InstanciaDeProceso();
        instanciaDeProceso.setProcesoLugar(procesoLugarGuardado);

        if (dto.getIdDeNivel() != null) {
            Nivel nivel = nivelRepository.findById(dto.getIdDeNivel())
                    .orElseThrow(() -> new EntityNotFoundException("El nivel con ID " + dto.getIdDeNivel() + " no existe."));
            instanciaDeProceso.setNivel(nivel);
        }

        if (dto.getIdDeProceso() != null) {
            Proceso proceso = procesoRepository.findById(dto.getIdDeProceso())
                    .orElseThrow(() -> new EntityNotFoundException("El proceso con ID " + dto.getIdDeProceso() + " no existe."));
            instanciaDeProceso.setProceso(proceso);
        }

        instanciaDeProceso.setFechaHoraDeInicio(dto.getFechaHoraDeInicio());
        instanciaDeProceso.setFechaHoraDeFin(dto.getFechaHoraDeFin());
        instanciaDeProceso.setGanadoresNum(dto.getGanadoresNum());

        InstanciaDeProceso instanciaGuardada = instanciaDeProcesoRepository.save(instanciaDeProceso);
        System.out.println("Nueva instancia de proceso registrada con ID: " + instanciaGuardada.getId());

        return instanciaGuardada.getId();
    }

    
    //ELiminar 
    
    @Transactional(readOnly = true)
    public List<String> verificarDependenciasCriticas(Long idDeInstancia) {
        List<String> tablasCriticas = new ArrayList<>();

        // Verificar si existen votos asociados a la instancia
        if (votoRepository.existsByInstanciaDeProcesoId(idDeInstancia)) {
            tablasCriticas.add("Votos");
        }

        // Verificar si existen candidaturas asociadas a la instancia
        if (candidaturaRepository.existsByInstanciaDeProcesoId(idDeInstancia)) {
            tablasCriticas.add("Candidaturas");
        }

        return tablasCriticas;
    }

    @Transactional
    public void eliminarInstancia(Long idDeInstancia) {
        // Buscar la instancia de proceso
        InstanciaDeProceso instancia = instanciaDeProcesoRepository.findById(idDeInstancia)
                .orElseThrow(() -> new EntityNotFoundException("La instancia con ID " + idDeInstancia + " no existe."));

        // Verificar dependencias críticas
        List<String> dependenciasCriticas = verificarDependenciasCriticas(idDeInstancia);
        if (!dependenciasCriticas.isEmpty()) {
            throw new IllegalStateException("No se puede eliminar la instancia. Relacionada con: " +
                    String.join(", ", dependenciasCriticas));
        }

        // Eliminar la instancia
        instanciaDeProcesoRepository.delete(instancia);

        // Eliminar el procesoLugar asociado
        ProcesoLugar procesoLugar = instancia.getProcesoLugar();
        if (procesoLugar != null) {
            procesoLugarRepository.delete(procesoLugar);
        }

        System.out.println("Instancia de proceso y su ProcesoLugar asociado eliminados correctamente.");
    }

    
    

}
