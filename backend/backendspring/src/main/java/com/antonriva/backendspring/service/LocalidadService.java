package com.antonriva.backendspring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.antonriva.backendspring.model.Localidad;
import com.antonriva.backendspring.repository.LocalidadRepository;

import jakarta.transaction.Transactional;

@Service
public class LocalidadService {
	
    private final LocalidadRepository localidadRepository;
    
    public LocalidadService(LocalidadRepository localidadRepository) {
    	this.localidadRepository = localidadRepository;
    }
    
    // Buscar municipios por ID de entidad federativa
    @Transactional
    public List<Localidad> obtenerLocalidadesPorMunicipioId(Long municipioId) {
        return localidadRepository.findByMunicipio_Id(municipioId);
    }


}
