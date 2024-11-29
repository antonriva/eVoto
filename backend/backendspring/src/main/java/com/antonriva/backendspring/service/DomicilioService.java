package com.antonriva.backendspring.service;

import com.antonriva.backendspring.model.Domicilio;
import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.model.PersonaDomicilio;
import com.antonriva.backendspring.repository.DomicilioRepository;
import com.antonriva.backendspring.repository.PersonaDomicilioRepository;
import com.antonriva.backendspring.specification.DomicilioSpecifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DomicilioService {
	

	
    @Autowired
    private DomicilioRepository domicilioRepository;

    @Autowired
    private PersonaDomicilioRepository personaDomicilioRepository;

    /**
     * Crear un nuevo domicilio.
     *
     * @param domicilio El domicilio a crear.
     * @return El domicilio creado.
     */
    public Domicilio registrarDomicilio(Domicilio domicilio) {
        return domicilioRepository.save(domicilio);
    }

    /**
     * Obtener todos los domicilios.
     *
     * @return Lista de domicilios.
     */
    public List<Domicilio> obtenerTodosLosDomicilios() {
        return domicilioRepository.findAll();
    }

    /**
     * Obtener un domicilio por su ID.
     *
     * @param id El ID del domicilio.
     * @return El domicilio si existe.
     */
    public Optional<Domicilio> obtenerDomicilioPorId(int id) {
        return domicilioRepository.findById(id);
    }
    
    // Eliminar un domicilio
    public void eliminarDomicilio(Integer id) {
        Domicilio domicilio = domicilioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Domicilio no encontrado con ID: " + id));

        // Eliminar relaciones en PersonaDomicilio
        List<PersonaDomicilio> relaciones = personaDomicilioRepository.findByDomicilio(domicilio);
        personaDomicilioRepository.deleteAll(relaciones);

        // Eliminar el domicilio
        domicilioRepository.delete(domicilio);
    }
    
    public List<Domicilio> buscarConFiltros(String calle, Integer numeroExterior, String municipio, String colonia) {
        Specification<Domicilio> specification = DomicilioSpecifications.conFiltros(calle, numeroExterior, municipio, colonia);
        return domicilioRepository.findAll(specification);
    }
    /**
     * Obtener todos los domicilios asociados a una persona específica.
     *
     * @param persona La persona para buscar sus domicilios.
     * @return Lista de domicilios asociados.
     */
    // Obtener domicilios por persona
    public List<Domicilio> obtenerDomiciliosPorPersona(Persona persona) {
        List<PersonaDomicilio> relaciones = personaDomicilioRepository.findByPersona(persona);
        return relaciones.stream()
                .map(PersonaDomicilio::getDomicilio) // Obtener los domicilios de las relaciones
                .collect(Collectors.toList());
    }
    /**
     * Actualizar un domicilio.
     *
     * @param id           El ID del domicilio a actualizar.
     * @param nuevoDomicilio Los datos nuevos del domicilio.
     * @return El domicilio actualizado.
     */
    @Transactional
    public Domicilio actualizarDomicilio(int id, Domicilio nuevoDomicilio) {
        return domicilioRepository.findById(id).map(domicilio -> {
            if (nuevoDomicilio.getCalle() != null) {
                domicilio.setCalle(nuevoDomicilio.getCalle());
            }
            if (nuevoDomicilio.getNumeroExterior() != 0) {
                domicilio.setNumeroExterior(nuevoDomicilio.getNumeroExterior());
            }
            if (nuevoDomicilio.getNumeroInterior() != 0) {
                domicilio.setNumeroInterior(nuevoDomicilio.getNumeroInterior());
            }
            if (nuevoDomicilio.getEntidadFederativa() != null) {
                domicilio.setEntidadFederativa(nuevoDomicilio.getEntidadFederativa());
            }
            if (nuevoDomicilio.getMunicipio() != null) {
                domicilio.setMunicipio(nuevoDomicilio.getMunicipio());
            }
            if (nuevoDomicilio.getColonia() != null) {
                domicilio.setColonia(nuevoDomicilio.getColonia());
            }
            if (nuevoDomicilio.getPostal() != null) {
                domicilio.setPostal(nuevoDomicilio.getPostal());
            }
            return domicilioRepository.save(domicilio);
        }).orElseThrow(() -> new RuntimeException("Domicilio no encontrado con ID: " + id));
    }

    /**
     * Eliminar un domicilio.
     *
     * @param id El ID del domicilio a eliminar.
     */
    @Transactional
    public void eliminarDomicilio(int id) {
        Domicilio domicilio = domicilioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Domicilio no encontrado con ID: " + id));
        
        // Eliminar relaciones en PersonaDomicilio
        personaDomicilioRepository.deleteByDomicilio(domicilio);
        
        domicilioRepository.delete(domicilio);
    }
}