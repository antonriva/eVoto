package com.antonriva.backendspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.antonriva.backendspring.model.Municipio;
import com.antonriva.backendspring.model.Proceso;
import com.antonriva.backendspring.repository.ProcesoRepository;

@Service
public class ProcesoService {


    private final ProcesoRepository procesoRepository;

    public ProcesoService(ProcesoRepository procesoRepository) {
        this.procesoRepository = procesoRepository;
    }

    /**
     * Obtiene todos los procesos disponibles.
     * 
     * @return Una lista de todos los procesos.
     */
    @Transactional(readOnly = true)
    public List<Proceso> obtenerTodosLosProcesos() {
        return procesoRepository.findAll();
    }

    /**
     * Obtiene un proceso por su ID.
     * 
     * @param id El ID del proceso.
     * @return El proceso correspondiente al ID proporcionado.
     * @throws IllegalArgumentException si no se encuentra el proceso.
     */
    @Transactional(readOnly = true)
    public Proceso obtenerProcesoPorId(Long id) {
        return procesoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Proceso no encontrado con el ID: " + id));
    }

    @Transactional
    public List<Proceso> obtenerMunicipiosPorNivelId(Long nivelId) {
        return procesoRepository.findByNivel_Id(nivelId);
    }

}
