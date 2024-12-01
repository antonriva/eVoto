package com.antonriva.backendspring.service;

import com.antonriva.backendspring.model.Partido;
import com.antonriva.backendspring.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    // Crear o actualizar un partido
    public Partido saveOrUpdatePartido(Partido partido) {
        return partidoRepository.save(partido);
    }

    // Obtener todos los partidos
    public List<Partido> getAllPartidos() {
        return partidoRepository.findAll();
    }

    // Obtener un partido por su ID
    public Optional<Partido> getPartidoById(int id) {
        return partidoRepository.findById(id);
    }

    // Eliminar un partido por su ID
    public void deletePartido(int id) {
        partidoRepository.deleteById(id);
    }
}
