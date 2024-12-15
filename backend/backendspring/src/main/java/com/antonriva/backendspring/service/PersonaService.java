package com.antonriva.backendspring.service;

import com.antonriva.backendspring.dto.PersonaBuscarCompletoDTO;
import com.antonriva.backendspring.dto.PersonaEditarDTO;
import com.antonriva.backendspring.dto.PersonaRegistroDTO;
import com.antonriva.backendspring.id.PersonaDomicilioId;
import com.antonriva.backendspring.model.Domicilio;
import com.antonriva.backendspring.model.Elector;
import com.antonriva.backendspring.model.EntidadFederativa;
import com.antonriva.backendspring.model.Municipio;
import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.model.PersonaDomicilio;
import com.antonriva.backendspring.model.RelacionFamiliar;
import com.antonriva.backendspring.model.TipoDeDomicilio;
import com.antonriva.backendspring.repository.DomicilioRepository;
import com.antonriva.backendspring.repository.ElectorRepository;
import com.antonriva.backendspring.repository.EntidadFederativaRepository;
import com.antonriva.backendspring.repository.MunicipioRepository;
import com.antonriva.backendspring.repository.PersonaDomicilioRepository;
import com.antonriva.backendspring.repository.PersonaRepository;
import com.antonriva.backendspring.repository.RelacionFamiliarRepository;
import com.antonriva.backendspring.repository.TipoDeDomicilioRepository;
import com.antonriva.backendspring.specification.PersonaSpecifications;

import jakarta.persistence.EntityNotFoundException;
//import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private final ElectorRepository electorRepository;
    private final EntidadFederativaRepository entidadFederativaRepository;
    private final MunicipioRepository municipioRepository;
    private final DomicilioRepository domicilioRepository;
    private final TipoDeDomicilioRepository tipoDeDomicilioRepository;
    
    

    public PersonaService(
    		PersonaRepository personaRepository, RelacionFamiliarRepository relacionFamiliarRepository, 
    		PersonaDomicilioRepository personaDomicilioRepository,
    		ElectorRepository electorRepository,
    		EntidadFederativaRepository entidadFederativaRepository,
    		MunicipioRepository municipioRepository, 
    		DomicilioRepository domicilioRepository,
    		TipoDeDomicilioRepository tipoDeDomicilioRepository
    		) {
        this.personaRepository = personaRepository;
        this.relacionFamiliarRepository = relacionFamiliarRepository;
        this.personaDomicilioRepository = personaDomicilioRepository;
        this.electorRepository = electorRepository;
        this.entidadFederativaRepository = entidadFederativaRepository;
        this.municipioRepository = municipioRepository;
        this.domicilioRepository = domicilioRepository;
        this.tipoDeDomicilioRepository = tipoDeDomicilioRepository;
    }
    
    
    
//SON PARA BUSQUEDAS 
    
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
    
    //ESTOS DOS SON PARA LOS PADRES
    @Transactional
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
    
    @Transactional
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
    //
    
    
    //EDITAR PERSONA, OBTENER DATOS QUE SE VAN A DESPLEGAR POR DEFAULT Y EDICION 
    
    @Transactional(readOnly = true)
    public PersonaEditarDTO obtenerDatosParaEdicion(Long id) {
        // Verificar si la persona existe
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La persona con ID " + id + " no existe."));

        // Obtener el domicilio asociado al tipo de domicilio de nacimiento (id = 1)
        Optional<PersonaDomicilio> personaDomicilioOpt = personaDomicilioRepository
                .findByPersonaIdAndTipoDeDomicilioId(id, 1L);

        // Variables para almacenar los IDs de entidad federativa, municipio y la fecha de inicio
        Long entidadFederativaId = null;
        Long municipioId = null;
        LocalDate fechaDeInicio = null;

        if (personaDomicilioOpt.isPresent()) {
            PersonaDomicilio personaDomicilio = personaDomicilioOpt.get();
            Domicilio domicilio = personaDomicilio.getDomicilio();

            // Obtener la fecha de inicio de la relación PersonaDomicilio
            fechaDeInicio = personaDomicilio.getFechaDeInicio();

            if (domicilio != null) {
                // Obtener ID de Entidad Federativa
                if (domicilio.getEntidadFederativa() != null) {
                    entidadFederativaId = domicilio.getEntidadFederativa().getId();
                }

                // Obtener ID de Municipio
                if (domicilio.getMunicipio() != null) {
                    municipioId = domicilio.getMunicipio().getId();
                }
            }
        }

        // Retornar el DTO con los datos recopilados
        return new PersonaEditarDTO(
                persona.getNombre(),
                persona.getApellidoPaterno(),
                persona.getApellidoMaterno(),
                persona.getFechaDeNacimiento(),
                persona.getFechaDeFin(),
                entidadFederativaId,
                municipioId,
                fechaDeInicio // Incluir la fecha de inicio en el DTO
        );
    }
    
    @Transactional
    public void editarPersona(Long id, PersonaEditarDTO dto) {
        // Imprimir datos recibidos
        System.out.println("Datos recibidos para actualizar la persona:");
        System.out.println("ID de Persona: " + id);
        System.out.println("Nombre: " + dto.getNombre());
        System.out.println("Apellido Paterno: " + dto.getApellidoPaterno());
        System.out.println("Apellido Materno: " + dto.getApellidoMaterno());
        System.out.println("Fecha de Nacimiento: " + dto.getFechaDeNacimiento());
        System.out.println("Entidad Federativa ID: " + dto.getEntidadFederativaId());
        System.out.println("Municipio ID: " + dto.getMunicipioId());
        System.out.println("Fecha de Inicio: " + dto.getFechaDeInicio());

        // 1. Buscar a la persona
        System.out.println("Buscando persona con ID: " + id);
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La persona con ID " + id + " no existe."));
        System.out.println("Persona encontrada: " + persona);

        // 2. Validar la coherencia de los datos de domicilio
        boolean entidadFederativaProporcionada = dto.getEntidadFederativaId() != null;
        boolean municipioProporcionado = dto.getMunicipioId() != null;
        System.out.println("Entidad Federativa proporcionada: " + entidadFederativaProporcionada);
        System.out.println("Municipio proporcionado: " + municipioProporcionado);

        if (entidadFederativaProporcionada ^ municipioProporcionado) { // XOR para validar que ambos o ninguno estén presentes
            System.out.println("Error: Se debe proporcionar tanto Entidad Federativa como Municipio, o ninguno.");
            throw new IllegalArgumentException("Debe proporcionar tanto Entidad Federativa como Municipio, o ninguno.");
        }

        // 3. Actualizar los datos básicos de la persona
        System.out.println("Actualizando datos básicos de la persona.");
        persona.setNombre(dto.getNombre());
        persona.setApellidoPaterno(dto.getApellidoPaterno());
        persona.setApellidoMaterno(dto.getApellidoMaterno());
        persona.setFechaDeNacimiento(dto.getFechaDeNacimiento());
        persona.setFechaDeFin(dto.getFechaDeFin());
        personaRepository.save(persona);
        System.out.println("Datos básicos de la persona actualizados.");

        // 4. Si no se proporcionaron datos de domicilio, salir del método
        if (!entidadFederativaProporcionada && !municipioProporcionado) {
            System.out.println("No se proporcionaron datos de Entidad Federativa o Municipio. Finalizando actualización.");
            return;
        }

        // 5. Buscar o crear el domicilio con las características proporcionadas
        System.out.println("Buscando domicilio con las características proporcionadas.");
        Domicilio domicilio = domicilioRepository
                .findByEntidadFederativaIdAndMunicipioIdAndCalleIsNullAndNumeroExteriorIsNullAndNumeroInteriorIsNullAndCodigoPostalIsNullAndColoniaIsNullAndLocalidadIsNull(
                        dto.getEntidadFederativaId(),
                        dto.getMunicipioId()
                )
                .orElseGet(() -> {
                    System.out.println("No se encontró un domicilio con las características proporcionadas. Creando uno nuevo.");
                    Domicilio nuevoDomicilio = new Domicilio();

                    // Configurar los valores del domicilio explícitamente
                    nuevoDomicilio.setEntidadFederativa(
                            entidadFederativaRepository.findById(dto.getEntidadFederativaId())
                                    .orElseThrow(() -> new EntityNotFoundException(
                                            "Entidad Federativa no encontrada con ID: " + dto.getEntidadFederativaId()))
                    );
                    nuevoDomicilio.setMunicipio(
                            municipioRepository.findById(dto.getMunicipioId())
                                    .orElseThrow(() -> new EntityNotFoundException(
                                            "Municipio no encontrado con ID: " + dto.getMunicipioId()))
                    );
                    nuevoDomicilio.setLocalidad(null);
                    nuevoDomicilio.setColonia(null);
                    nuevoDomicilio.setCodigoPostal(null);
                    nuevoDomicilio.setCalle(null);
                    nuevoDomicilio.setNumeroExterior(null);
                    nuevoDomicilio.setNumeroInterior(null);

                    System.out.println("Nuevo domicilio a guardar:");
                    System.out.println("Entidad Federativa: " + nuevoDomicilio.getEntidadFederativa());
                    System.out.println("Municipio: " + nuevoDomicilio.getMunicipio());
                    return domicilioRepository.save(nuevoDomicilio);
                });

        System.out.println("Domicilio seleccionado o creado: " + domicilio);

        // 6. Validar si ya existe una relación de tipo 1
        System.out.println("Verificando si ya existe una relación PersonaDomicilio de tipo 1.");
        Optional<PersonaDomicilio> relacionExistente = personaDomicilioRepository
                .findByPersonaIdAndTipoDeDomicilioId(persona.getId(), 1L);

        if (relacionExistente.isPresent()) {
            System.out.println("Se encontró una relación de tipo 1. Eliminándola para evitar duplicados.");
            personaDomicilioRepository.delete(relacionExistente.get());
            System.out.println("Relación de tipo 1 eliminada.");
        } else {
            System.out.println("No se encontró una relación de tipo 1 existente.");
        }

        // 7. Crear la nueva relación PersonaDomicilio
        System.out.println("Creando nueva relación PersonaDomicilio.");

        PersonaDomicilio nuevaRelacion = new PersonaDomicilio();

        // Configurar el ID compuesto
        PersonaDomicilioId idCompuesto = new PersonaDomicilioId(persona.getId(), domicilio.getId());
        nuevaRelacion.setId(idCompuesto); // Establece el ID compuesto

        // Configurar los demás atributos
        nuevaRelacion.setPersona(persona);
        nuevaRelacion.setDomicilio(domicilio);
        nuevaRelacion.setFechaDeInicio(dto.getFechaDeInicio()); // Fecha de inicio proporcionada en el DTO
        nuevaRelacion.setFechaDeFin(null); // Dejar fecha de fin en null
        nuevaRelacion.setTipoDeDomicilio(
                tipoDeDomicilioRepository.findById(1L)
                        .orElseThrow(() -> new EntityNotFoundException("Tipo de domicilio 1 no encontrado."))
        );

        // Imprimir los valores configurados para verificar
        System.out.println("Nueva relación PersonaDomicilio configurada:");
        System.out.println("ID de Persona: " + nuevaRelacion.getPersona().getId());
        System.out.println("ID de Domicilio: " + nuevaRelacion.getDomicilio().getId());
        System.out.println("Fecha de Inicio: " + nuevaRelacion.getFechaDeInicio());
        System.out.println("Fecha de Fin: " + nuevaRelacion.getFechaDeFin());
        System.out.println("Tipo de Domicilio: " + nuevaRelacion.getTipoDeDomicilio().getId());

        // Guardar la nueva relación
        personaDomicilioRepository.save(nuevaRelacion);

        System.out.println("Relación PersonaDomicilio guardada exitosamente.");
        System.out.println("Proceso de actualización de persona finalizado.");
    }
 
   
    //REGISTRO Y VERIFICACION DE EXISTENCIA PREVIA

    @Transactional
    public Persona registrarPersona(PersonaEditarDTO dto) {
        // Imprimir datos recibidos
        System.out.println("Datos recibidos para registrar la persona:");
        System.out.println("Nombre: " + dto.getNombre());
        System.out.println("Apellido Paterno: " + dto.getApellidoPaterno());
        System.out.println("Apellido Materno: " + dto.getApellidoMaterno());
        System.out.println("Fecha de Nacimiento: " + dto.getFechaDeNacimiento());
        System.out.println("Entidad Federativa ID: " + dto.getEntidadFederativaId());
        System.out.println("Municipio ID: " + dto.getMunicipioId());
        System.out.println("Fecha de Inicio: " + dto.getFechaDeInicio());

        // Validar la coherencia de los datos de domicilio
        boolean entidadFederativaProporcionada = dto.getEntidadFederativaId() != null;
        boolean municipioProporcionado = dto.getMunicipioId() != null;
        System.out.println("Entidad Federativa proporcionada: " + entidadFederativaProporcionada);
        System.out.println("Municipio proporcionado: " + municipioProporcionado);

        if (entidadFederativaProporcionada ^ municipioProporcionado) { // XOR para validar que ambos o ninguno estén presentes
            System.out.println("Error: Se debe proporcionar tanto Entidad Federativa como Municipio, o ninguno.");
            throw new IllegalArgumentException("Debe proporcionar tanto Entidad Federativa como Municipio, o ninguno.");
        }

        // 2. Crear la nueva persona
        System.out.println("Creando nueva persona.");
        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre(dto.getNombre());
        nuevaPersona.setApellidoPaterno(dto.getApellidoPaterno());
        nuevaPersona.setApellidoMaterno(dto.getApellidoMaterno());
        nuevaPersona.setFechaDeNacimiento(dto.getFechaDeNacimiento());
        personaRepository.save(nuevaPersona);
        System.out.println("Persona creada: " + nuevaPersona);

        // 3. Si no se proporcionaron datos de domicilio, terminar
        if (!entidadFederativaProporcionada && !municipioProporcionado) {
            System.out.println("No se proporcionaron datos de Entidad Federativa o Municipio. Finalizando registro.");
            return nuevaPersona;
        }

        // 4. Buscar o crear el domicilio con las características proporcionadas
        System.out.println("Buscando domicilio con las características proporcionadas.");
        Domicilio domicilio = domicilioRepository
                .findByEntidadFederativaIdAndMunicipioIdAndCalleIsNullAndNumeroExteriorIsNullAndNumeroInteriorIsNullAndCodigoPostalIsNullAndColoniaIsNullAndLocalidadIsNull(
                        dto.getEntidadFederativaId(),
                        dto.getMunicipioId()
                )
                .orElseGet(() -> {
                    System.out.println("No se encontró un domicilio con las características proporcionadas. Creando uno nuevo.");
                    Domicilio nuevoDomicilio = new Domicilio();

                    // Configurar los valores del domicilio explícitamente
                    nuevoDomicilio.setEntidadFederativa(
                            entidadFederativaRepository.findById(dto.getEntidadFederativaId())
                                    .orElseThrow(() -> new EntityNotFoundException(
                                            "Entidad Federativa no encontrada con ID: " + dto.getEntidadFederativaId()))
                    );
                    nuevoDomicilio.setMunicipio(
                            municipioRepository.findById(dto.getMunicipioId())
                                    .orElseThrow(() -> new EntityNotFoundException(
                                            "Municipio no encontrado con ID: " + dto.getMunicipioId()))
                    );
                    nuevoDomicilio.setLocalidad(null);
                    nuevoDomicilio.setColonia(null);
                    nuevoDomicilio.setCodigoPostal(null);
                    nuevoDomicilio.setCalle(null);
                    nuevoDomicilio.setNumeroExterior(null);
                    nuevoDomicilio.setNumeroInterior(null);

                    System.out.println("Nuevo domicilio a guardar:");
                    System.out.println("Entidad Federativa: " + nuevoDomicilio.getEntidadFederativa());
                    System.out.println("Municipio: " + nuevoDomicilio.getMunicipio());
                    return domicilioRepository.save(nuevoDomicilio);
                });

        System.out.println("Domicilio seleccionado o creado: " + domicilio);

        // 5. Crear la relación PersonaDomicilio
        System.out.println("Creando nueva relación PersonaDomicilio.");
        PersonaDomicilio nuevaRelacion = new PersonaDomicilio();

        // Configurar el ID compuesto
        PersonaDomicilioId idCompuesto = new PersonaDomicilioId(nuevaPersona.getId(), domicilio.getId());
        nuevaRelacion.setId(idCompuesto); // Establece el ID compuesto

        // Configurar los demás atributos
        nuevaRelacion.setPersona(nuevaPersona);
        nuevaRelacion.setDomicilio(domicilio);
        nuevaRelacion.setFechaDeInicio(dto.getFechaDeInicio()); // Fecha de inicio proporcionada en el DTO
        nuevaRelacion.setFechaDeFin(null); // Dejar fecha de fin en null
        nuevaRelacion.setTipoDeDomicilio(
                tipoDeDomicilioRepository.findById(1L)
                        .orElseThrow(() -> new EntityNotFoundException("Tipo de domicilio 1 no encontrado."))
        );

        System.out.println("Nueva relación PersonaDomicilio configurada:");
        System.out.println("ID de Persona: " + nuevaRelacion.getPersona().getId());
        System.out.println("ID de Domicilio: " + nuevaRelacion.getDomicilio().getId());
        System.out.println("Fecha de Inicio: " + nuevaRelacion.getFechaDeInicio());
        System.out.println("Fecha de Fin: " + nuevaRelacion.getFechaDeFin());
        System.out.println("Tipo de Domicilio: " + nuevaRelacion.getTipoDeDomicilio().getId());

        // Guardar la nueva relación
        personaDomicilioRepository.save(nuevaRelacion);

        System.out.println("Relación PersonaDomicilio guardada exitosamente.");

        return nuevaPersona;
    }
    
    public Optional<Persona> buscarPersonaExacta(PersonaEditarDTO dto) {
        System.out.println("Validando si ya existe una persona con las características proporcionadas:");

        // Log de los datos para verificar
        System.out.println("Nombre: " + dto.getNombre());
        System.out.println("Apellido Paterno: " + dto.getApellidoPaterno());
        System.out.println("Apellido Materno: " + dto.getApellidoMaterno());
        System.out.println("Fecha de Nacimiento: " + dto.getFechaDeNacimiento());
        System.out.println("Entidad Federativa ID: " + dto.getEntidadFederativaId());
        System.out.println("Municipio ID: " + dto.getMunicipioId());

        return personaRepository.findPersonaExacta(
                dto.getNombre(),
                dto.getApellidoPaterno(),
                dto.getApellidoMaterno(),
                dto.getFechaDeNacimiento(),
                dto.getEntidadFederativaId(),
                dto.getMunicipioId()
        );
    
}


    //ELIMINAR PERSONA DOS METODOS
    @Transactional
    public void eliminarPersona(Long id) {
        // Verificar si la persona existe
        Optional<Persona> persona = personaRepository.findById(id);
        if (persona.isEmpty()) {
            throw new EntityNotFoundException("La persona con ID " + id + " no existe.");
        }

        // Verificar dependencias críticas
        List<String> dependenciasCriticas = verificarDependenciasCriticas(id);
        if (!dependenciasCriticas.isEmpty()) {
            throw new IllegalStateException("No se puede eliminar la persona. Relacionada con: " +
                    String.join(", ", dependenciasCriticas));
        }

        // Eliminar relaciones permisibles
        personaDomicilioRepository.deleteByPersonaId(id);
        relacionFamiliarRepository.deleteByPersonaId(id);

        // Eliminar la persona
        personaRepository.deleteById(id);
    }

    private List<String> verificarDependenciasCriticas(Long id) {
        List<String> tablasCriticas = new ArrayList<>();

        // Verificar relaciones en Elector
        if (electorRepository.existsByPersonaId(id)) {
            tablasCriticas.add("Elector");
        }

        return tablasCriticas;
    }

    

}


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
/*

    
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

    */

