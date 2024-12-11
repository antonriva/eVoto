package com.antonriva.backendspring.service;

import com.antonriva.backendspring.model.EntidadFederativa;
import com.antonriva.backendspring.repository.EntidadFederativaRepository;
import com.antonriva.backendspring.specification.EntidadFederativaSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntidadFederativaService {
	/*
    @Autowired
    private EntidadFederativaRepository entidadFederativaRepository;

    // Crear una nueva entidad federativa
    public EntidadFederativa registrarEntidadFederativa(EntidadFederativa entidadFederativa) {
        return entidadFederativaRepository.save(entidadFederativa);
    }

    // Obtener todas las entidades federativas
    public List<EntidadFederativa> obtenerTodasLasEntidades() {
        return entidadFederativaRepository.findAll();
    }

    // Obtener una entidad federativa por ID
    public Optional<EntidadFederativa> obtenerEntidadPorId(int id) {
        return entidadFederativaRepository.findById(id);
    }

    // Buscar entidades federativas con filtros
    public List<EntidadFederativa> buscarConFiltros(String descripcion) {
        Specification<EntidadFederativa> specification = EntidadFederativaSpecifications.conDescripcion(descripcion);
        return entidadFederativaRepository.findAll(specification);
    }

    // Actualizar una entidad federativa
    public EntidadFederativa actualizarEntidad(int id, EntidadFederativa nuevaEntidad) {
        return entidadFederativaRepository.findById(id).map(entidad -> {
            if (nuevaEntidad.getDescripcion() != null) {
                entidad.setDescripcion(nuevaEntidad.getDescripcion());
            }
            return entidadFederativaRepository.save(entidad);
        }).orElseThrow(() -> new RuntimeException("Entidad federativa no encontrada con ID: " + id));
    }

    // Eliminar una entidad federativa
    public void eliminarEntidad(int id) {
        if (!entidadFederativaRepository.existsById(id)) {
            throw new RuntimeException("Entidad federativa no encontrada con ID: " + id);
        }
        entidadFederativaRepository.deleteById(id);
    }
    */
}

