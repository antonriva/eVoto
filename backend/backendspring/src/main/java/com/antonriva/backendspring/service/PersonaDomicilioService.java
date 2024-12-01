package com.antonriva.backendspring.service;

import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.dto.DetalleDomicilioDTO;
import com.antonriva.backendspring.model.Domicilio;
import com.antonriva.backendspring.model.PersonaDomicilio;
import com.antonriva.backendspring.repository.PersonaDomicilioRepository;
import com.antonriva.backendspring.repository.PersonaRepository;
import com.antonriva.backendspring.specification.PersonaDomicilioSpecifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaDomicilioService {
	
    @Autowired
    private PersonaRepository personaRepository;

    public List<DetalleDomicilioDTO> obtenerDomiciliosPorPersona(int idPersona) {
        Persona persona = personaRepository.findById(idPersona)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        List<DetalleDomicilioDTO> detalles = new ArrayList<>();

        for (PersonaDomicilio relacion : persona.getPersonaDomicilios()) {
            DetalleDomicilioDTO detalle = new DetalleDomicilioDTO();
            Domicilio domicilio = relacion.getDomicilio();

            detalle.setCalle(domicilio.getCalle());
            detalle.setNumeroExterior(domicilio.getNumeroExterior());
            detalle.setMunicipio(domicilio.getMunicipio().getDescripcion());
            detalle.setColonia(domicilio.getColonia().getDescripcion());
            detalle.setEntidadFederativa(domicilio.getEntidadFederativa().getDescripcion());
            detalle.setCodigoPostal(String.valueOf(domicilio.getPostal().getDescripcion())); // Conversión
            detalle.setFechaDeInicio(relacion.getFechaDeInicio());
            detalle.setFechaDeFin(relacion.getFechaDeFin());

            detalles.add(detalle);
        }

        return detalles;
    }
    


    @Autowired
    private PersonaDomicilioRepository personaDomicilioRepository;

    /**
     * Crear una nueva relación entre Persona y Domicilio.
     *
     * @param persona   La persona a asociar.
     * @param domicilio El domicilio a asociar.
     * @param fechaDeInicio Fecha en la que comienza la relación.
     * @return La relación creada.
     */
    @Transactional
    public PersonaDomicilio crearRelacion(Persona persona, Domicilio domicilio, LocalDate fechaDeInicio) {
    	
    	
        // Verificar si ya existe la relación
        Optional<PersonaDomicilio> existente = personaDomicilioRepository.findByPersonaAndDomicilio(persona, domicilio);
        if (existente.isPresent()) {
            throw new RuntimeException("La relación entre la persona y el domicilio ya existe.");
        }

        // Crear y guardar la nueva relación
        PersonaDomicilio personaDomicilio = new PersonaDomicilio();
        personaDomicilio.setPersona(persona);
        personaDomicilio.setDomicilio(domicilio);
        personaDomicilio.setFechaDeInicio(fechaDeInicio);
        return personaDomicilioRepository.save(personaDomicilio);
    }

    public List<PersonaDomicilio> buscarConFiltros(Integer idPersona, Integer idDomicilio, LocalDate fechaDeInicio, LocalDate fechaDeFin) {
        Specification<PersonaDomicilio> specification = Specification
                .where(PersonaDomicilioSpecifications.conPersonaId(idPersona))
                .and(PersonaDomicilioSpecifications.conDomicilioId(idDomicilio))
                .and(PersonaDomicilioSpecifications.conFechaDeInicio(fechaDeInicio))
                .and(PersonaDomicilioSpecifications.conFechaDeFin(fechaDeFin));

        return personaDomicilioRepository.findAll(specification);
    }

    /**
     * Obtener todas las relaciones existentes.
     *
     * @return Una lista de todas las relaciones.
     */
    public List<PersonaDomicilio> obtenerTodasLasRelaciones() {
        return personaDomicilioRepository.findAll();
    }

    /**
     * Obtener las relaciones de una persona específica.
     *
     * @param persona La persona cuyas relaciones se desean obtener.
     * @return Lista de relaciones de la persona.
     */
    public List<PersonaDomicilio> obtenerRelacionesPorPersona(Persona persona) {
        return personaDomicilioRepository.findByPersona(persona);
    }

    /**
     * Obtener las relaciones de un domicilio específico.
     *
     * @param domicilio El domicilio cuyas relaciones se desean obtener.
     * @return Lista de relaciones del domicilio.
     */
    public List<PersonaDomicilio> obtenerRelacionesPorDomicilio(Domicilio domicilio) {
        return personaDomicilioRepository.findByDomicilio(domicilio);
    }

    /**
     * Eliminar una relación específica.
     *
     * @param persona   La persona asociada a la relación.
     * @param domicilio El domicilio asociado a la relación.
     */
    @Transactional
    public void eliminarRelacion(Persona persona, Domicilio domicilio) {
        Optional<PersonaDomicilio> relacion = personaDomicilioRepository.findByPersonaAndDomicilio(persona, domicilio);
        if (relacion.isPresent()) {
            personaDomicilioRepository.delete(relacion.get());
        } else {
            throw new RuntimeException("No se encontró la relación para eliminar.");
        }
    }

    /**
     * Actualizar la fecha de fin de una relación específica.
     *
     * @param persona     La persona asociada.
     * @param domicilio   El domicilio asociado.
     * @param fechaDeFin  Nueva fecha de fin.
     * @return La relación actualizada.
     */
    @Transactional
    public PersonaDomicilio actualizarFechaDeFin(Persona persona, Domicilio domicilio, LocalDate fechaDeFin) {
        PersonaDomicilio relacion = personaDomicilioRepository.findByPersonaAndDomicilio(persona, domicilio)
            .orElseThrow(() -> new RuntimeException("No se encontró la relación para actualizar."));

        relacion.setFechaDeFin(fechaDeFin);
        return personaDomicilioRepository.save(relacion);
    }
}
