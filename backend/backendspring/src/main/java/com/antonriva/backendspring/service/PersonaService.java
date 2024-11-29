package com.antonriva.backendspring.service;

import com.antonriva.backendspring.model.Domicilio;
import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.model.PersonaDomicilio;
import com.antonriva.backendspring.repository.DomicilioRepository;
import com.antonriva.backendspring.repository.PersonaDomicilioRepository;
import com.antonriva.backendspring.repository.PersonaRepository;
import com.antonriva.backendspring.specification.PersonaSpecifications;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaDomicilioRepository personaDomicilioRepository;

    @Autowired
    private DomicilioRepository domicilioRepository;

    // Crear una nueva relación entre Persona y Domicilio
    public PersonaDomicilio asignarDomicilioAPersona(Integer personaId, Integer domicilioId, LocalDate fechaDeInicio) {
        Persona persona = personaRepository.findById(personaId)
            .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        Domicilio domicilio = domicilioRepository.findById(domicilioId)
            .orElseThrow(() -> new RuntimeException("Domicilio no encontrado"));

        Optional<PersonaDomicilio> existente = personaDomicilioRepository.findByPersonaAndDomicilio(persona, domicilio);
        if (existente.isPresent()) {
            throw new RuntimeException("La relación ya existe");
        }

        PersonaDomicilio personaDomicilio = new PersonaDomicilio(persona, domicilio, fechaDeInicio);
        return personaDomicilioRepository.save(personaDomicilio);
    }
    
    public Optional<Persona> obtenerPersonaConDomicilios(Integer personaId) {
        return personaRepository.findById(personaId).map(persona -> {
            // Cargar relaciones de domicilios
            List<PersonaDomicilio> domicilios = personaDomicilioRepository.findByPersona(persona);
            persona.setPersonaDomicilios(domicilios);
            return persona;
        });
    }
    // Crear o registrar una persona
    public Persona registrarPersona(Persona persona) {
        if (personaRepository.existsByNombreAndFechaDeNacimiento(persona.getNombre(), persona.getFechaDeNacimiento())) {
            throw new RuntimeException("La persona ya existe");
        }
        return personaRepository.save(persona);
    }

    // Obtener todas las personas
    public List<Persona> obtenerTodasLasPersonas() {
        return personaRepository.findAll();
    }

    // Obtener una persona por ID
    public Optional<Persona> obtenerPersonaPorId(int id) {
        return personaRepository.findById(id);
    }

    // Buscar personas con filtros
    public List<Persona> buscarConFiltros(Integer id, String nombre, String apellido, String fechaNacimiento) {
        Specification<Persona> specification = Specification
                .where(PersonaSpecifications.conId(id))
                .and(PersonaSpecifications.conNombre(nombre))
                .and(PersonaSpecifications.conApellido(apellido))
                .and(PersonaSpecifications.conFechaDeNacimiento(fechaNacimiento));

        return personaRepository.findAll(specification);
    }

    // Actualizar una persona
    @Transactional
    public Persona actualizarPersona(int id, Persona nuevaPersona) {
        return personaRepository.findById(id).map(persona -> {
            if (nuevaPersona.getNombre() != null) {
                persona.setNombre(nuevaPersona.getNombre());
            }
            if (nuevaPersona.getApellidoPaterno() != null) {
                persona.setApellidoPaterno(nuevaPersona.getApellidoPaterno());
            }
            if (nuevaPersona.getApellidoMaterno() != null) {
                persona.setApellidoMaterno(nuevaPersona.getApellidoMaterno());
            }
            if (nuevaPersona.getFechaDeNacimiento() != null) {
                persona.setFechaDeNacimiento(nuevaPersona.getFechaDeNacimiento());
            }
            if (nuevaPersona.getFechaDeFin() != null) {
                persona.setFechaDeFin(nuevaPersona.getFechaDeFin());
            }
            return personaRepository.save(persona);
        }).orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + id));
    }

    // Eliminar una persona
    @Transactional
    public void eliminarPersona(int id) {
        Persona persona = personaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + id));
        personaDomicilioRepository.deleteByPersona(persona);
        personaRepository.delete(persona);
    }
}
