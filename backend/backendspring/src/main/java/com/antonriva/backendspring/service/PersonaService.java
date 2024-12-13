package com.antonriva.backendspring.service;

import com.antonriva.backendspring.dto.PersonaBuscarCompletoDTO;
import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.model.RelacionFamiliar;
import com.antonriva.backendspring.repository.PersonaDomicilioRepository;
import com.antonriva.backendspring.repository.PersonaRepository;
import com.antonriva.backendspring.repository.RelacionFamiliarRepository;
import com.antonriva.backendspring.specification.PersonaSpecifications;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PersonaService {
	
    private final PersonaRepository personaRepository;
    private final RelacionFamiliarRepository relacionFamiliarRepository;
    private final PersonaDomicilioRepository personaDomicilioRepository;
    

    public PersonaService(PersonaRepository personaRepository, RelacionFamiliarRepository relacionFamiliarRepository, PersonaDomicilioRepository personaDomicilioRepository) {
        this.personaRepository = personaRepository;
        this.relacionFamiliarRepository = relacionFamiliarRepository;
        this.personaDomicilioRepository = personaDomicilioRepository;
    }
    
    @Transactional
    public List<PersonaBuscarCompletoDTO> buscarPersonasConDetalles(
    		Long id,
            String nombre,
            String apellidoPaterno,
            String apellidoMaterno,
            Integer anioNacimiento,
            Integer mesNacimiento,
            Integer diaNacimiento,
            Integer anioFin,
            Integer mesFin,
            Integer diaFin,
            Long entidadFederativa, 
            Long municipio,
            Long localidad,
            Long colonia,
            Long codigoPostal,
            Long tipoDeDomicilioId
            
    		) {
    	
        Logger log = LoggerFactory.getLogger(PersonaService.class);
    	
    	try {

        // Construir la Specification dinámica
            Specification<Persona> spec = Specification.where(PersonaSpecifications.conId(id))
                    .and(PersonaSpecifications.conNombre(nombre))
                    .and(PersonaSpecifications.conApellidoPaterno(apellidoPaterno))
                    .and(PersonaSpecifications.conApellidoMaterno(apellidoMaterno))
                    .and(PersonaSpecifications.conFechaDeNacimiento(anioNacimiento, mesNacimiento, diaNacimiento))
                    .and(PersonaSpecifications.conFechaDeFin(anioFin, mesFin, diaFin))
                    .and(PersonaSpecifications.conEntidadFederativa(entidadFederativa))
                    .and(PersonaSpecifications.conMunicipio(municipio))
                    .and(PersonaSpecifications.conLocalidad(localidad))
                    .and(PersonaSpecifications.conColonia(colonia))
                    .and(PersonaSpecifications.conCodigoPostal(codigoPostal))
                    .and(PersonaSpecifications.conTipoDeDomicilio(tipoDeDomicilioId));

        // Consultar personas que coincidan
        List<Persona> personas = personaRepository.findAll(spec);

        // Transformar cada persona en un DTO con información adicional
        return personas.stream().map(persona -> {


            // Construir el DTO principal
            return new PersonaBuscarCompletoDTO(
                    persona.getId(),
                    persona.getNombre(),
                    persona.getApellidoPaterno(),
                    persona.getApellidoMaterno(),
                    persona.getFechaDeNacimiento(),
                    persona.getFechaDeFin()
            );
        }).toList();
    	} catch (Exception e) {
            // Manejo de excepciones
            log.error("Error al buscar personas con detalles", e);
            throw new RuntimeException("Ocurrió un error al buscar personas con detalles. Por favor intente nuevamente.");
        }
    }
    
    
    public Map<String, PersonaBuscarCompletoDTO> obtenerPadres(Long idPersona) {
        // Buscar la relación familiar de la persona
        Optional<RelacionFamiliar> relacionOptional = relacionFamiliarRepository.findRelacionFamiliarByPersonaId(idPersona);

        if (relacionOptional.isEmpty()) {
            throw new RuntimeException("No se encontró la relación familiar para la persona con ID: " + idPersona);
        }

        RelacionFamiliar relacion = relacionOptional.get();
        Map<String, PersonaBuscarCompletoDTO> padres = new HashMap<>();

        // Mapear al padre si existe
        if (relacion.getPadre() != null) {
            padres.put("padre", mapearAPersonaDTO(relacion.getPadre()));
        }

        // Mapear a la madre si existe
        if (relacion.getMadre() != null) {
            padres.put("madre", mapearAPersonaDTO(relacion.getMadre()));
        }

        return padres;
    }

    private PersonaBuscarCompletoDTO mapearAPersonaDTO(Persona persona) {
        return new PersonaBuscarCompletoDTO(
                persona.getId(),
                persona.getNombre(),
                persona.getApellidoPaterno(),
                persona.getApellidoMaterno(),
                persona.getFechaDeNacimiento(),
                persona.getFechaDeFin()
        );
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
/*
    public List<PersonaResumenDTO> obtenerResumenDePersonas() {
        List<Persona> personas = personaRepository.findAll();
        List<PersonaResumenDTO> listaResumen = new ArrayList<>();

        for (Persona persona : personas) {
            PersonaResumenDTO dto = new PersonaResumenDTO();
            dto.setId(persona.getId());
            dto.setNombre(persona.getNombre());
            dto.setApellidoPaterno(persona.getApellidoPaterno());
            dto.setApellidoMaterno(persona.getApellidoMaterno());
            dto.setFechaDeNacimiento(persona.getFechaDeNacimiento());
            dto.setFechaDeFin(persona.getFechaDeFin());
            dto.setCantidadDomicilios(persona.getPersonaDomicilios() != null ? persona.getPersonaDomicilios().size() : 0);

            listaResumen.add(dto);
        }

        return listaResumen;
    }
    
    public List<PersonaResumenDTO> buscarConFiltros(
            Integer id, String nombre, String apellidoPaterno, String apellidoMaterno,
            String fechaDeNacimiento, String fechaDeFin, Integer cantidadDomicilios) {

        Specification<Persona> specification = Specification
                .where(PersonaSpecifications.conId(id))
                .and(PersonaSpecifications.conNombre(nombre))
                .and(PersonaSpecifications.conApellidoPaterno(apellidoPaterno))
                .and(PersonaSpecifications.conApellidoMaterno(apellidoMaterno))
                .and(PersonaSpecifications.conFechaDeNacimiento(fechaDeNacimiento))
                .and(PersonaSpecifications.conFechaDeFin(fechaDeFin));

        List<Persona> personas = personaRepository.findAll(specification);
        List<PersonaResumenDTO> listaResumen = new ArrayList<>();

        for (Persona persona : personas) {
            // Calcular cantidad de domicilios
            int cantidadDomiciliosActual = persona.getPersonaDomicilios() != null ? persona.getPersonaDomicilios().size() : 0;

            // Si cantidadDomicilios es nulo, no se filtra por este campo
            if (cantidadDomicilios != null && cantidadDomicilios != cantidadDomiciliosActual) {
                continue;
            }

            PersonaResumenDTO dto = new PersonaResumenDTO();
            dto.setId(persona.getId());
            dto.setNombre(persona.getNombre());
            dto.setApellidoPaterno(persona.getApellidoPaterno());
            dto.setApellidoMaterno(persona.getApellidoMaterno());
            dto.setFechaDeNacimiento(persona.getFechaDeNacimiento());
            dto.setFechaDeFin(persona.getFechaDeFin());
            dto.setCantidadDomicilios(cantidadDomiciliosActual);

            listaResumen.add(dto);
        }

        return listaResumen;
    }
    
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


    // Actualizar una persona
    //SI FUNCIONA, TODOS LOS DATOS DEBEN PASARSE PARA QUE FUNCIONE
    @Transactional
    public Persona actualizarPersona(int id, Persona nuevaPersona) {
        return personaRepository.findById(id).map(persona -> {
            // Validate and normalize incoming data
            if (nuevaPersona.getNombre() != null) {
                persona.setNombre(nuevaPersona.getNombre().toUpperCase().trim());
            }
            if (nuevaPersona.getApellidoPaterno() != null) {
                persona.setApellidoPaterno(nuevaPersona.getApellidoPaterno().toUpperCase().trim());
            }
            if (nuevaPersona.getApellidoMaterno() != null) {
                persona.setApellidoMaterno(nuevaPersona.getApellidoMaterno().toUpperCase().trim());
            }
            if (nuevaPersona.getFechaDeNacimiento() != null) {
                persona.setFechaDeNacimiento(nuevaPersona.getFechaDeNacimiento());
            }
            if (nuevaPersona.getFechaDeFin() != null) {
                persona.setFechaDeFin(nuevaPersona.getFechaDeFin());
            }

            // Save changes
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
    */

