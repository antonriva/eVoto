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
	/*

    @Autowired
    private DomicilioRepository domicilioRepository;

    @Autowired
    private PersonaDomicilioRepository personaDomicilioRepository;

    public Domicilio registrarDomicilio(Domicilio domicilio) {
        return domicilioRepository.save(domicilio);
    }

    public List<Domicilio> obtenerTodosLosDomicilios() {
        return domicilioRepository.findAll();
    }

    public Optional<Domicilio> obtenerDomicilioPorId(int id) {
        return domicilioRepository.findById(id);
    }

    public List<Domicilio> buscarConFiltros(String calle, Integer numeroExterior, String municipio, String colonia) {
        Specification<Domicilio> specification = DomicilioSpecifications.conFiltros(calle, numeroExterior, municipio, colonia);
        return domicilioRepository.findAll(specification);
    }

    public List<Domicilio> obtenerDomiciliosPorPersona(Persona persona) {
        List<PersonaDomicilio> relaciones = personaDomicilioRepository.findByPersona(persona);
        return relaciones.stream().map(PersonaDomicilio::getDomicilio).collect(Collectors.toList());
    }

    @Transactional
    public Domicilio actualizarDomicilio(int id, Domicilio nuevoDomicilio) {
        return domicilioRepository.findById(id).map(domicilio -> {
            if (nuevoDomicilio.getCalle() != null) {
                domicilio.setCalle(nuevoDomicilio.getCalle().toUpperCase().trim());
            }
            if (nuevoDomicilio.getNumeroExterior() != null) {
                domicilio.setNumeroExterior(nuevoDomicilio.getNumeroExterior());
            }
            if (nuevoDomicilio.getNumeroInterior() != null) {
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
            return domicilioRepository.save(domicilio);
        }).orElseThrow(() -> new RuntimeException("Domicilio no encontrado con ID: " + id));
    }

    @Transactional
    public void eliminarDomicilio(int id) {
        Domicilio domicilio = domicilioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Domicilio no encontrado con ID: " + id));
        personaDomicilioRepository.deleteByDomicilio(domicilio);
        domicilioRepository.delete(domicilio);
    }
    */
}
