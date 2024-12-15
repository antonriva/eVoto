package com.antonriva.backendspring.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.antonriva.backendspring.dto.PartidoBuscarDetalleDTO;
import com.antonriva.backendspring.dto.PartidoEditarDTO;
import com.antonriva.backendspring.dto.PartidoRegistrarDTO;
import com.antonriva.backendspring.model.Partido;
import com.antonriva.backendspring.model.Visual;
import com.antonriva.backendspring.repository.CandidaturaRepository;
import com.antonriva.backendspring.repository.PartidoRepository;
import com.antonriva.backendspring.repository.VisualRepository;
import com.antonriva.backendspring.specification.PartidoSpecifications;

import jakarta.persistence.EntityNotFoundException;




@Service
public class PartidoService {

    private final PartidoRepository partidoRepository;
    private final VisualRepository visualRepository;
    private final CandidaturaRepository candidaturaRepository;
    
    public PartidoService (PartidoRepository partidoRepository, VisualRepository visualRepository, CandidaturaRepository candidaturaRepository) {
    	this.partidoRepository = partidoRepository;
    	this.visualRepository = visualRepository;
    	this.candidaturaRepository = candidaturaRepository;
    	
    }
//BUSCAR CON DETALLE, NOS REGRESA URL DEL LOGO
    @Transactional
    public List<PartidoBuscarDetalleDTO> buscarPartidosConDetalles(
            Long id,
            String denominacion,
            String siglas,
            Integer anioInicio,
            Integer mesInicio,
            Integer diaInicio,
            Integer anioFin,
            Integer mesFin,
            Integer diaFin
    ) {
        Logger log = LoggerFactory.getLogger(PartidoService.class);

        try {
            // Construir la Specification dinámica
            Specification<Partido> spec = Specification.where(PartidoSpecifications.conId(id))
                    .and(PartidoSpecifications.conDenominacion(denominacion))
                    .and(PartidoSpecifications.conSiglas(siglas))
                    .and(PartidoSpecifications.conFechaDeInicio(anioInicio, mesInicio, diaInicio))
                    .and(PartidoSpecifications.conFechaDeFin(anioFin, mesFin, diaFin));

            // Consultar partidos que coincidan
            List<Partido> partidos = partidoRepository.findAll(spec);

            // Transformar cada partido en un DTO con información adicional
            return partidos.stream().map(partido -> {
                // Buscar el visual correspondiente con vigencia ID 1 y tipo de visual ID 1
                Optional<Visual> visualOpt = visualRepository.findByPartidoAndRecursoVigenteIdAndTipoDeVisualId(partido, 1L, 1L);

                // Obtener la URL del visual (si existe)
                String visualUrl = visualOpt.map(Visual::getContenido).orElse(null);

                // Construir el DTO
                return new PartidoBuscarDetalleDTO(
                        partido.getId(),
                        partido.getDenominacion(),
                        partido.getSiglas(),
                        partido.getFechaDeInicio(),
                        partido.getFechaDeFin(),
                        visualUrl // URL del visual
                );
            }).toList();
        } catch (Exception e) {
            log.error("Error al buscar partidos con detalles", e);
            throw new RuntimeException("Ocurrió un error al buscar partidos con detalles. Por favor intente nuevamente.");
        }
    }

    
    //EDITAR PARTIDO
    @Transactional(readOnly = true)
    public PartidoEditarDTO obtenerDatosParaEdicion(Long id) {
        // Verificar si el partido existe
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El partido con ID " + id + " no existe."));

        // Obtener el logo vigente asociado (si existe)
        Optional<Visual> visualOpt = visualRepository
                .findByPartidoIdAndTipoDeVisualIdAndRecursoVigenteId(id, 1L, 1L);

        String visualUrl = null;
        if (visualOpt.isPresent()) {
            visualUrl = visualOpt.get().getContenido();
        }

        // Retornar el DTO con los datos recopilados
        return new PartidoEditarDTO(
                partido.getId(),
                partido.getDenominacion(),
                partido.getSiglas(),
                partido.getFechaDeInicio(),
                partido.getFechaDeFin(),
                visualUrl // URL del logo vigente, si existe
        );
    }
    
    @Transactional
    public void editarPartido(Long id, PartidoEditarDTO dto) {
        // Imprimir valores recibidos en el DTO
        System.out.println("=== INICIANDO ACTUALIZACIÓN DEL PARTIDO ===");
        System.out.println("Datos recibidos para actualizar el partido:");
        System.out.println("ID de Partido: " + id);
        System.out.println("Denominación: " + (dto.getDenominacion() != null ? dto.getDenominacion() : "null"));
        System.out.println("Siglas: " + (dto.getSiglas() != null ? dto.getSiglas() : "null"));
        System.out.println("Fecha de Inicio: " + (dto.getFechaDeInicio() != null ? dto.getFechaDeInicio() : "null"));
        System.out.println("Fecha de Fin: " + (dto.getFechaDeFin() != null ? dto.getFechaDeFin() : "null"));

        // Buscar el partido en la base de datos
        System.out.println("Buscando partido con ID: " + id);
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El partido con ID " + id + " no existe."));
        System.out.println("Partido encontrado:");
        System.out.println("ID: " + partido.getId());
        System.out.println("Denominación Actual: " + partido.getDenominacion());
        System.out.println("Siglas Actuales: " + partido.getSiglas());
        System.out.println("Fecha de Inicio Actual: " + partido.getFechaDeInicio());
        System.out.println("Fecha de Fin Actual: " + partido.getFechaDeFin());

        // Verificar antes de asignar los nuevos valores
        System.out.println("=== ASIGNANDO NUEVOS VALORES AL PARTIDO ===");
        if (dto.getDenominacion() != null) {
            System.out.println("Actualizando denominación: " + dto.getDenominacion());
            partido.setDenominacion(dto.getDenominacion());
        } else {
            System.out.println("Denominación recibida es nula. No se actualiza.");
        }

        if (dto.getSiglas() != null) {
            System.out.println("Actualizando siglas: " + dto.getSiglas());
            partido.setSiglas(dto.getSiglas());
        } else {
            System.out.println("Siglas recibidas son nulas. No se actualizan.");
        }

        if (dto.getFechaDeInicio() != null) {
            System.out.println("Actualizando fecha de inicio: " + dto.getFechaDeInicio());
            partido.setFechaDeInicio(dto.getFechaDeInicio());
        } else {
            System.out.println("Fecha de inicio recibida es nula. No se actualiza.");
        }

            System.out.println("Actualizando fecha de fin: " + dto.getFechaDeFin());
            partido.setFechaDeFin(dto.getFechaDeFin());

        // Guardar los cambios
        System.out.println("=== GUARDANDO CAMBIOS EN LA BASE DE DATOS ===");
        partidoRepository.save(partido);
        System.out.println("Datos del partido actualizados correctamente.");
    }

    
    @Transactional
    public void eliminarPartido(Long id) {
        // Verificar si el partido existe
        Optional<Partido> partidoOpt = partidoRepository.findById(id);
        if (partidoOpt.isEmpty()) {
            throw new EntityNotFoundException("El partido con ID " + id + " no existe.");
        }
        Partido partido = partidoOpt.get();

        // Verificar dependencias críticas
        List<String> dependenciasCriticas = verificarDependenciasCriticas(id);
        if (!dependenciasCriticas.isEmpty()) {
            throw new IllegalStateException("No se puede eliminar el partido. Relacionado con: " +
                    String.join(", ", dependenciasCriticas));
        }

        // Eliminar todos los logos asociados al partido en la tabla Visual
        System.out.println("Eliminando logos relacionados con el partido.");
        visualRepository.deleteByPartidoId(id);

        // Eliminar el partido
        System.out.println("Eliminando el partido con ID: " + id);
        partidoRepository.deleteById(id);
        System.out.println("Partido eliminado correctamente.");
    }

    private List<String> verificarDependenciasCriticas(Long id) {
        List<String> tablasCriticas = new ArrayList<>();

        // Verificar relaciones en Candidatura
        if (candidaturaRepository.existsByPartidoId(id)) {
            tablasCriticas.add("Candidatura");
        }

        return tablasCriticas;
    }
    
    @Transactional(readOnly = true)
    public Optional<Partido> verificarPartidoExistente(String denominacion, String siglas) {
        System.out.println("Verificando si ya existe un partido con denominación: " + denominacion + " o siglas: " + siglas);

        return partidoRepository.findByDenominacionOrSiglas(denominacion, siglas);
    }
    
    @Transactional
    public Partido registrarPartido(PartidoRegistrarDTO dto) {
        System.out.println("Registrando partido con los siguientes datos:");
        System.out.println("Denominación: " + dto.getDenominacion());
        System.out.println("Siglas: " + dto.getSiglas());
        System.out.println("Fecha de Inicio: " + dto.getFechaDeInicio());

        Partido nuevoPartido = new Partido();
        nuevoPartido.setDenominacion(dto.getDenominacion());
        nuevoPartido.setSiglas(dto.getSiglas());
        nuevoPartido.setFechaDeInicio(dto.getFechaDeInicio());

        partidoRepository.save(nuevoPartido);
        System.out.println("Partido registrado exitosamente: ID " + nuevoPartido.getId());

        return nuevoPartido;
    }
    

}
