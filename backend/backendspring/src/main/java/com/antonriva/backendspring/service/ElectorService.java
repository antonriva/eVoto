package com.antonriva.backendspring.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.antonriva.backendspring.dto.ElectorBuscarDTO;
import com.antonriva.backendspring.model.Elector;
import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.repository.ElectorRepository;
import com.antonriva.backendspring.specification.ElectorSpecifications;

import jakarta.transaction.Transactional;

@Service
public class ElectorService {

    private final ElectorRepository electorRepository;

    public ElectorService(ElectorRepository electorRepository) {
        this.electorRepository = electorRepository;
    }
    
    @Transactional
    public List<ElectorBuscarDTO> buscarElectorConDetalles(
            Long idDePersona,
            Long idDeElector,
            String nombre,
            String apellidoPaterno,
            String apellidoMaterno,
            Integer anioNacimiento,
            Integer mesNacimiento,
            Integer diaNacimiento,
            Integer anioFin,
            Integer mesFin,
            Integer diaFin,
            Long entidadFederativa, 
            Long municipio,
            Long localidad,
            Long colonia,
            Long codigoPostal,
            Long tipoDeDomicilioId
    ) {
        Logger log = LoggerFactory.getLogger(ElectorService.class);

        try {
            // Construir la Specification dinámica
            Specification<Elector> spec = Specification.where(ElectorSpecifications.conId(idDeElector))
            		.and(ElectorSpecifications.conIdDePersona(idDePersona))
                    .and(ElectorSpecifications.conNombreDePersona(nombre))
                    .and(ElectorSpecifications.conApellidoPaternoDePersona(apellidoPaterno))
                    .and(ElectorSpecifications.conApellidoMaternoDePersona(apellidoMaterno))
                    .and(ElectorSpecifications.conFechaDeNacimiento(anioNacimiento, mesNacimiento, diaNacimiento))
                    .and(ElectorSpecifications.conFechaDeFin(anioFin, mesFin, diaFin))
                    .and(ElectorSpecifications.conEntidadFederativa(entidadFederativa))
                    .and(ElectorSpecifications.conMunicipio(municipio))
                    .and(ElectorSpecifications.conLocalidad(localidad))
                    .and(ElectorSpecifications.conColonia(colonia))
                    .and(ElectorSpecifications.conCodigoPostal(codigoPostal))
                    .and(ElectorSpecifications.conTipoDeDomicilio(tipoDeDomicilioId));

            // Consultar los electores que coincidan
            List<Elector> electores = electorRepository.findAll(spec);

            // Transformar cada Elector en un DTO con información adicional
            return electores.stream().map(elector -> {
                Persona persona = elector.getPersona();

                // Construir el DTO principal
                return new ElectorBuscarDTO(
                        elector.getId(),
                        persona.getId(),
                        persona.getNombre(),
                        persona.getApellidoPaterno(),
                        persona.getApellidoMaterno(),
                        persona.getFechaDeNacimiento(),
                        persona.getFechaDeFin()
                );
            }).toList();
        } catch (Exception e) {
            // Manejo de excepciones
            log.error("Error al buscar electores con detalles", e);
            throw new RuntimeException("Ocurrió un error al buscar electores con detalles. Por favor intente nuevamente.");
        }
    }


    public void eliminarElector(Long id) {
        electorRepository.deleteById(id);
    }
}