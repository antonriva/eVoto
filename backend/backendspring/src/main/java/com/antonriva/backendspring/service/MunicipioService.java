package com.antonriva.backendspring.service;

import com.antonriva.backendspring.model.Municipio;
import com.antonriva.backendspring.repository.MunicipioRepository;
import com.antonriva.backendspring.specification.MunicipioSpecifications;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipioService {
	
    private final MunicipioRepository municipioRepository;
    
    public MunicipioService(MunicipioRepository municipioRepository) {
    	this.municipioRepository = municipioRepository;
    }
    
    // Buscar municipios por ID de entidad federativa
    @Transactional
    public List<Municipio> obtenerMunicipiosPorEntidadFederativaId(Long entidadFederativaId) {
        return municipioRepository.findByEntidadFederativa_Id(entidadFederativaId);
    }


	/*

    // Crear un nuevo municipio
    public Municipio registrarMunicipio(Municipio municipio) {
        return municipioRepository.save(municipio);
    }

    // Obtener todos los municipios
    public List<Municipio> obtenerTodosLosMunicipios() {
        return municipioRepository.findAll();
    }

    // Obtener un municipio por ID
    public Optional<Municipio> obtenerMunicipioPorId(int id) {
        return municipioRepository.findById(id);
    }


    // Buscar municipios con filtros
    public List<Municipio> buscarConFiltros(String descripcion, Integer entidadFederativaId) {
        Specification<Municipio> specification = MunicipioSpecifications.conFiltros(descripcion, entidadFederativaId);
        return municipioRepository.findAll(specification);
    }

    // Actualizar un municipio
    public Municipio actualizarMunicipio(int id, Municipio nuevoMunicipio) {
        return municipioRepository.findById(id).map(municipio -> {
            if (nuevoMunicipio.getDescripcion() != null) {
                municipio.setDescripcion(nuevoMunicipio.getDescripcion());
            }
            if (nuevoMunicipio.getEntidadFederativa() != null) {
                municipio.setEntidadFederativa(nuevoMunicipio.getEntidadFederativa());
            }
            return municipioRepository.save(municipio);
        }).orElseThrow(() -> new RuntimeException("Municipio no encontrado con ID: " + id));
    }

    // Eliminar un municipio
    public void eliminarMunicipio(int id) {
        if (!municipioRepository.existsById(id)) {
            throw new RuntimeException("Municipio no encontrado con ID: " + id);
        }
        municipioRepository.deleteById(id);
    }
    */
}
