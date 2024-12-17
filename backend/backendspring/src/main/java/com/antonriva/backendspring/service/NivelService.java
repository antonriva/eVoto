package com.antonriva.backendspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.antonriva.backendspring.model.Nivel;
import com.antonriva.backendspring.repository.NivelRepository;

@Service
public class NivelService {
	

    private final NivelRepository nivelRepository;

    public NivelService(NivelRepository nivelRepository) {
        this.nivelRepository = nivelRepository;
    }

    /**
     * Obtiene todos los niveles disponibles.
     * 
     * @return Una lista de todos los niveles.
     */
    @Transactional(readOnly = true)
    public List<Nivel> obtenerTodosLosNiveles() {
        return nivelRepository.findAll();
    }

    /**
     * Obtiene un nivel por su ID.
     * 
     * @param id El ID del nivel.
     * @return El nivel correspondiente al ID proporcionado.
     * @throws IllegalArgumentException si no se encuentra el nivel.
     */
    @Transactional(readOnly = true)
    public Nivel obtenerNivelPorId(Long id) {
        return nivelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nivel no encontrado con el ID: " + id));
    }

}
