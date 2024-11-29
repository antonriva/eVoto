package com.antonriva.backendspring.service;

import com.antonriva.backendspring.model.Postal;
import com.antonriva.backendspring.repository.PostalRepository;
import com.antonriva.backendspring.specification.PostalSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostalService {

    @Autowired
    private PostalRepository postalRepository;

    // Crear un nuevo código postal
    public Postal registrarPostal(Postal postal) {
        return postalRepository.save(postal);
    }

    // Obtener todos los códigos postales
    public List<Postal> obtenerTodosLosPostales() {
        return postalRepository.findAll();
    }

    // Obtener un código postal por ID
    public Optional<Postal> obtenerPostalPorId(int id) {
        return postalRepository.findById(id);
    }

    // Buscar códigos postales por ID de colonia
    public List<Postal> obtenerPostalesPorColoniaId(int coloniaId) {
        return postalRepository.findByColonia_Id(coloniaId);
    }

    // Buscar códigos postales con filtros
    public List<Postal> buscarConFiltros(Integer descripcion, Integer coloniaId) {
        Specification<Postal> specification = PostalSpecifications.conFiltros(descripcion, coloniaId);
        return postalRepository.findAll(specification);
    }

    // Actualizar un código postal
    public Postal actualizarPostal(int id, Postal nuevoPostal) {
        return postalRepository.findById(id).map(postal -> {
            if (nuevoPostal.getDescripcion() != 0) {
                postal.setDescripcion(nuevoPostal.getDescripcion());
            }
            if (nuevoPostal.getColonia() != null) {
                postal.setColonia(nuevoPostal.getColonia());
            }
            return postalRepository.save(postal);
        }).orElseThrow(() -> new RuntimeException("Código postal no encontrado con ID: " + id));
    }

    // Eliminar un código postal
    public void eliminarPostal(int id) {
        if (!postalRepository.existsById(id)) {
            throw new RuntimeException("Código postal no encontrado con ID: " + id);
        }
        postalRepository.deleteById(id);
    }
}
