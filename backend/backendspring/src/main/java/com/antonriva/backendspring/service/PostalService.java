package com.antonriva.backendspring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.antonriva.backendspring.model.Colonia;
import com.antonriva.backendspring.model.Postal;
import com.antonriva.backendspring.repository.ColoniaRepository;
import com.antonriva.backendspring.repository.PostalRepository;

import jakarta.transaction.Transactional;

@Service
public class PostalService {
	
    private final PostalRepository postalRepository;
    
    public PostalService(PostalRepository postalRepository) {
    	this.postalRepository = postalRepository;
    }
    
    // Buscar municipios por ID de entidad federativa
    @Transactional
    public List<Postal> obtenerPostalPorColoniaId(Long coloniaId) {
        return postalRepository.findByColonia_Id(coloniaId);
    }
    

}
