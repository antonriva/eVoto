package com.antonriva.backendspring.service;

import com.antonriva.backendspring.model.Colonia;
import com.antonriva.backendspring.repository.ColoniaRepository;
import com.antonriva.backendspring.specification.ColoniaSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColoniaService {

    @Autowired
    private ColoniaRepository coloniaRepository;

    // Crear una nueva colonia
    public Colonia registrarColonia(Colonia colonia) {
        return coloniaRepository.save(colonia);
    }

    // Obtener todas las colonias
    public List<Colonia> obtenerTodasLasColonias() {
        return coloniaRepository.findAll();
    }

    // Obtener una colonia por ID
    public Optional<Colonia> obtenerColoniaPorId(int id) {
        return coloniaRepository.findById(id);
    }

    // Buscar colonias por ID de municipio
    public List<Colonia> obtenerColoniasPorMunicipioId(int municipioId) {
        return coloniaRepository.findByMunicipio_Id(municipioId);
    }

    // Buscar colonias con filtros
    public List<Colonia> buscarConFiltros(String descripcion, Integer municipioId) {
        Specification<Colonia> specification = ColoniaSpecifications.conFiltros(descripcion, municipioId);
        return coloniaRepository.findAll(specification);
    }

    // Actualizar una colonia
    public Colonia actualizarColonia(int id, Colonia nuevaColonia) {
        return coloniaRepository.findById(id).map(colonia -> {
            if (nuevaColonia.getDescripcion() != null) {
                colonia.setDescripcion(nuevaColonia.getDescripcion());
            }
            if (nuevaColonia.getMunicipio() != null) {
                colonia.setMunicipio(nuevaColonia.getMunicipio());
            }
            return coloniaRepository.save(colonia);
        }).orElseThrow(() -> new RuntimeException("Colonia no encontrada con ID: " + id));
    }

    // Eliminar una colonia
    public void eliminarColonia(int id) {
        if (!coloniaRepository.existsById(id)) {
            throw new RuntimeException("Colonia no encontrada con ID: " + id);
        }
        coloniaRepository.deleteById(id);
    }
}
