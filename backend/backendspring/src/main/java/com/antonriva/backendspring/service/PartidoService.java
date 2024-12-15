package com.antonriva.backendspring.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.antonriva.backendspring.dto.PartidoBuscarDetalleDTO;
import com.antonriva.backendspring.dto.PartidoEditarDTO;
import com.antonriva.backendspring.model.Partido;
import com.antonriva.backendspring.model.Visual;
import com.antonriva.backendspring.repository.PartidoRepository;
import com.antonriva.backendspring.repository.VisualRepository;
import com.antonriva.backendspring.specification.PartidoSpecifications;

import jakarta.persistence.EntityNotFoundException;




@Service
public class PartidoService {

    private final PartidoRepository partidoRepository;
    private final VisualRepository visualRepository;
    
    public PartidoService (PartidoRepository partidoRepository, VisualRepository visualRepository) {
    	this.partidoRepository = partidoRepository;
    	this.visualRepository = visualRepository;
    	
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
    
    
    
    
    public void eliminarPartido(Long id) {
        partidoRepository.deleteById(id);
    }
}
