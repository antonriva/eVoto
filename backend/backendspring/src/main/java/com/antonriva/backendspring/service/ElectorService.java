package com.antonriva.backendspring.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.antonriva.backendspring.dto.DomicilioDTO;
import com.antonriva.backendspring.dto.ElectorBuscarCompletoDTO;
import com.antonriva.backendspring.dto.ElectorBuscarDTO;
import com.antonriva.backendspring.dto.ElectorEditarDTO;
import com.antonriva.backendspring.id.PersonaDomicilioId;
import com.antonriva.backendspring.model.Domicilio;
import com.antonriva.backendspring.model.Elector;
import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.model.PersonaDomicilio;
import com.antonriva.backendspring.repository.ColoniaRepository;
import com.antonriva.backendspring.repository.DomicilioRepository;
import com.antonriva.backendspring.repository.ElectorCandidaturaRepository;
import com.antonriva.backendspring.repository.ElectorInstanciaRepository;
import com.antonriva.backendspring.repository.ElectorRepository;
import com.antonriva.backendspring.repository.EntidadFederativaRepository;
import com.antonriva.backendspring.repository.LocalidadRepository;
import com.antonriva.backendspring.repository.MunicipioRepository;
import com.antonriva.backendspring.repository.PersonaDomicilioRepository;
import com.antonriva.backendspring.repository.PersonaRepository;
import com.antonriva.backendspring.repository.PostalRepository;
import com.antonriva.backendspring.repository.TipoDeDomicilioRepository;
import com.antonriva.backendspring.specification.ElectorSpecifications;

import jakarta.persistence.EntityNotFoundException;



@Service
public class ElectorService {

    private final ElectorRepository electorRepository;
    private final PersonaRepository personaRepository;
    private final PersonaDomicilioRepository personaDomicilioRepository;
    private final DomicilioRepository domicilioRepository;
    private final EntidadFederativaRepository entidadFederativaRepository;
    private final MunicipioRepository municipioRepository;
    private final LocalidadRepository localidadRepository;
    private final PostalRepository postalRepository;
    private final ColoniaRepository coloniaRepository;
    private final TipoDeDomicilioRepository tipoDeDomicilioRepository;
    private final ElectorInstanciaRepository electorInstanciaRepository;
    private final ElectorCandidaturaRepository electorCandidaturaRepository;
    
    
    public ElectorService(
    		ElectorRepository electorRepository, 
    		PersonaDomicilioRepository personaDomicilioRepository, 
    		DomicilioRepository domicilioRepository,
    		EntidadFederativaRepository entidadFederativaRepository,
    		MunicipioRepository municipioRepository, 
    		LocalidadRepository localidadRepository,
    		PostalRepository postalRepository,
    		ColoniaRepository coloniaRepository,
    		TipoDeDomicilioRepository tipoDeDomicilioRepository, 
    		PersonaRepository personaRepository,
    		ElectorInstanciaRepository electorInstanciaRepository,
    		ElectorCandidaturaRepository electorCandidaturaRepository
    		) {
        this.electorRepository = electorRepository;
        this.personaDomicilioRepository = personaDomicilioRepository;
        this.domicilioRepository = domicilioRepository;
        this.entidadFederativaRepository = entidadFederativaRepository;
        this.municipioRepository = municipioRepository;
        this.localidadRepository = localidadRepository;
        this.coloniaRepository = coloniaRepository;
        this.tipoDeDomicilioRepository = tipoDeDomicilioRepository;
        this.postalRepository = postalRepository;
        this.personaRepository = personaRepository;
        this.electorInstanciaRepository = electorInstanciaRepository;
        this.electorCandidaturaRepository = electorCandidaturaRepository;
    }
    //BUSCAR COMPLETO
    @Transactional
    public List<ElectorBuscarCompletoDTO> buscarElectorConDetalles(
            Long idElector,
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
            Integer anioInicioElector,
            Integer mesInicioElector,
            Integer diaInicioElector,
            Long entidadFederativa,
            Long municipio,
            Long localidad,
            Long colonia,
            Long codigoPostal,
            Long tipoDeDomicilioId
    ) {
        Logger log = LoggerFactory.getLogger(ElectorService.class);

        try {
            // Construir la Specification dinámica
            Specification<Elector> spec = Specification.where(ElectorSpecifications.conIdElector(idElector))
                    .and(ElectorSpecifications.conIdPersona(id))
                    .and(ElectorSpecifications.conNombrePersona(nombre))
                    .and(ElectorSpecifications.conApellidoPaternoPersona(apellidoPaterno))
                    .and(ElectorSpecifications.conApellidoMaternoPersona(apellidoMaterno))
                    .and(ElectorSpecifications.conFechaDeNacimientoPersona(anioNacimiento, mesNacimiento, diaNacimiento))
                    .and(ElectorSpecifications.conFechaDeFin(anioFin, mesFin, diaFin))
                    .and(ElectorSpecifications.conFechaDeInicioElector(anioInicioElector, mesInicioElector, diaInicioElector))
                    .and(ElectorSpecifications.conEntidadFederativa(entidadFederativa))
                    .and(ElectorSpecifications.conMunicipioPersona(municipio))
                    .and(ElectorSpecifications.conLocalidad(localidad))
                    .and(ElectorSpecifications.conColonia(colonia))
                    .and(ElectorSpecifications.conCodigoPostal(codigoPostal))
                    .and(ElectorSpecifications.conTipoDeDomicilio(tipoDeDomicilioId));

            // Consultar electores que coincidan
            List<Elector> electores = electorRepository.findAll(spec);

            // Transformar cada elector en un DTO con información adicional
            return electores.stream().map(elector -> {
                Persona persona = elector.getPersona();

                // Construir el DTO principal
                return new ElectorBuscarCompletoDTO(
                        elector.getId(),
                        elector.getFechaDeInicio(),
                        elector.getFechaDeFin(),
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
            log.error("Error al buscar electores con detalles", e);
            throw new RuntimeException("Ocurrió un error al buscar electores con detalles. Por favor intente nuevamente.");
        }
    }


    //EDITAR, regresar los valores
    
    @Transactional(readOnly = true)
    public ElectorEditarDTO obtenerDatosParaEdicion(Long idElector) {
        // Buscar el elector
        Elector elector = electorRepository.findById(idElector)
                .orElseThrow(() -> new EntityNotFoundException("El elector con ID " + idElector + " no existe."));

        // Obtener la persona asociada
        Persona persona = elector.getPersona();

        // Buscar el domicilio de tipo 2 (residencia)
        Optional<PersonaDomicilio> personaDomicilioOpt = personaDomicilioRepository
                .findByPersonaIdAndTipoDeDomicilioId(persona.getId(), 2L);

        // Inicializar campos del domicilio
        Long entidadFederativaId = null;
        Long municipioId = null;
        Long localidadId = null;
        Long coloniaId = null;
        Long codigoPostalId = null;
        String calle = null;
        Integer numeroExterior = null;
        Integer numeroInterior = null;
        LocalDate fechaDeInicioDomicilio = null; // Fecha de inicio de la relación PersonaDomicilio


        if (personaDomicilioOpt.isPresent()) {
            PersonaDomicilio personaDomicilio = personaDomicilioOpt.get();
            fechaDeInicioDomicilio = personaDomicilio.getFechaDeInicio();

            Domicilio domicilio = personaDomicilioOpt.get().getDomicilio();

            if (domicilio != null) {
                entidadFederativaId = domicilio.getEntidadFederativa() != null ? domicilio.getEntidadFederativa().getId() : null;
                municipioId = domicilio.getMunicipio() != null ? domicilio.getMunicipio().getId() : null;
                localidadId = domicilio.getLocalidad() != null ? domicilio.getLocalidad().getId() : null;
                coloniaId = domicilio.getColonia() != null ? domicilio.getColonia().getId() : null;
                codigoPostalId = domicilio.getCodigoPostal() != null ? domicilio.getCodigoPostal().getId() : null;
                calle = domicilio.getCalle();
                numeroExterior = domicilio.getNumeroExterior();
                numeroInterior = domicilio.getNumeroInterior();
            }
        }

        // Crear y retornar el DTO
        return new ElectorEditarDTO(
                elector.getId(),
                persona.getId(),
                entidadFederativaId,
                municipioId,
                localidadId,
                coloniaId,
                codigoPostalId,
                calle,
                numeroExterior,
                numeroInterior,
                fechaDeInicioDomicilio, // Incluir la fecha de inicio del domicilio
                elector.getFechaDeInicio()
        );
    }
    
            //EDICION
    
    @Transactional
    public void editarElector(Long idElector, ElectorEditarDTO dto) {
        System.out.println("Datos recibidos para actualizar el elector:");
        System.out.println("ID de Elector: " + idElector);
        System.out.println("Entidad Federativa: " + dto.getEntidadFederativaId());
        System.out.println("Municipio: " + dto.getMunicipioId());
        System.out.println("Fecha de Inicio: " + dto.getFechaDeInicio());

        // 1. Buscar el elector
        Elector elector = electorRepository.findById(idElector)
                .orElseThrow(() -> new EntityNotFoundException("El elector con ID " + idElector + " no existe."));

        // 2. Verificar coherencia de datos de domicilio
        boolean entidadFederativaProporcionada = dto.getEntidadFederativaId() != null;
        boolean municipioProporcionado = dto.getMunicipioId() != null;

        if (entidadFederativaProporcionada ^ municipioProporcionado) {
            throw new IllegalArgumentException("Debe proporcionar tanto Entidad Federativa como Municipio, o ninguno.");
        }

        // 3. Validar y eliminar relación existente de tipo 2 antes de crear una nueva
        if (entidadFederativaProporcionada && municipioProporcionado) {
            Persona persona = elector.getPersona();
            System.out.println("Verificando si ya existe una relación PersonaDomicilio de tipo 2.");
            Optional<PersonaDomicilio> relacionExistente = personaDomicilioRepository
                    .findByPersonaIdAndTipoDeDomicilioId(persona.getId(), 2L);

            if (relacionExistente.isPresent()) {
                System.out.println("Se encontró una relación de tipo 2. Eliminándola para evitar duplicados.");
                personaDomicilioRepository.delete(relacionExistente.get());
                System.out.println("Relación de tipo 2 eliminada.");
            } else {
                System.out.println("No se encontró una relación de tipo 2 existente.");
            }

            // 4. Buscar o crear el domicilio
            Domicilio domicilio = domicilioRepository
                    .findByEntidadFederativaIdAndMunicipioIdAndLocalidadIdAndColoniaIdAndCodigoPostalIdAndCalleAndNumeroExteriorAndNumeroInterior(
                            dto.getEntidadFederativaId(),
                            dto.getMunicipioId(),
                            dto.getLocalidadId(),
                            dto.getColoniaId(),
                            dto.getCodigoPostalId(),
                            dto.getCalle(),
                            dto.getNumeroExterior(),
                            dto.getNumeroInterior()
                    ).orElseGet(() -> {
                        // Crear un nuevo domicilio
                        System.out.println("Creando un nuevo domicilio con las características proporcionadas.");
                        Domicilio nuevoDomicilio = new Domicilio();
                        nuevoDomicilio.setEntidadFederativa(
                                entidadFederativaRepository.findById(dto.getEntidadFederativaId())
                                        .orElseThrow(() -> new EntityNotFoundException("Entidad Federativa no encontrada."))
                        );
                        nuevoDomicilio.setMunicipio(
                                municipioRepository.findById(dto.getMunicipioId())
                                        .orElseThrow(() -> new EntityNotFoundException("Municipio no encontrado."))
                        );
                        nuevoDomicilio.setLocalidad(localidadRepository.findById(dto.getLocalidadId()).orElse(null));
                        nuevoDomicilio.setColonia(coloniaRepository.findById(dto.getColoniaId()).orElse(null));
                        nuevoDomicilio.setCodigoPostal(postalRepository.findById(dto.getCodigoPostalId()).orElse(null));
                        nuevoDomicilio.setCalle(dto.getCalle());
                        nuevoDomicilio.setNumeroExterior(dto.getNumeroExterior());
                        nuevoDomicilio.setNumeroInterior(dto.getNumeroInterior());
                        return domicilioRepository.save(nuevoDomicilio);
                    });

            System.out.println("Domicilio seleccionado o creado: " + domicilio);

            // 5. Asociar el domicilio al elector
         // Crear la nueva relación PersonaDomicilio
            PersonaDomicilio nuevaRelacion = new PersonaDomicilio();

            // Configurar el ID compuesto de PersonaDomicilio
            PersonaDomicilioId idCompuesto = new PersonaDomicilioId();
            idCompuesto.setIdDePersona(persona.getId());
            idCompuesto.setIdDeDomicilio(domicilio.getId());
            nuevaRelacion.setId(idCompuesto); // Asignar el ID compuesto

            // Asignar los demás atributos
            nuevaRelacion.setPersona(persona);
            nuevaRelacion.setDomicilio(domicilio);
            nuevaRelacion.setTipoDeDomicilio(
                tipoDeDomicilioRepository.findById(2L)
                        .orElseThrow(() -> new EntityNotFoundException("Tipo de domicilio no encontrado."))
            );
            nuevaRelacion.setFechaDeInicio(dto.getFechaDeInicio());

            // Guardar la nueva relación
            personaDomicilioRepository.save(nuevaRelacion);
            System.out.println("Nueva relación PersonaDomicilio guardada exitosamente.");
        // 6. Actualizar la fecha de inicio del elector
        if (dto.getFechaDeInicioElector() != null) {
            System.out.println("Actualizando la fecha de inicio del elector.");
            elector.setFechaDeInicio(dto.getFechaDeInicioElector());
            electorRepository.save(elector);
            System.out.println("Fecha de inicio del elector actualizada correctamente.");
        }

        System.out.println("Elector actualizado correctamente.");
    }


}


    //REGISTRAR ELECTOR 
    
    
    
    @Transactional
    public Elector registrarElector(Long idPersona, LocalDate fechaDeInicioElector, DomicilioDTO domicilioDTO) {
        try {
            System.out.println("Iniciando registro de elector para la persona con ID: " + idPersona);

            // Verificar si la persona existe
            System.out.println("Verificando si la persona existe...");
            Persona persona = personaRepository.findById(idPersona)
                    .orElseThrow(() -> new EntityNotFoundException("La persona con ID " + idPersona + " no existe."));
            System.out.println("Persona encontrada: " + persona);

            // Validar que la persona tiene al menos 17 años
            System.out.println("Validando la edad de la persona...");
            LocalDate fechaActual = LocalDate.now();
            if (persona.getFechaDeNacimiento() == null || fechaActual.minusYears(17).isBefore(persona.getFechaDeNacimiento())) {
                System.out.println("La persona no cumple con la edad mínima de 17 años.");
                throw new IllegalArgumentException("La persona debe tener al menos 17 años para ser registrada como elector.");
            }
            System.out.println("Validación de edad completada.");

            // Validar que la fecha de inicio del elector no sea nula
            System.out.println("Validando la fecha de inicio del elector...");
            if (fechaDeInicioElector == null) {
                System.out.println("La fecha de inicio del elector no fue proporcionada.");
                throw new IllegalArgumentException("La fecha de inicio del elector es obligatoria.");
            }
            System.out.println("Fecha de inicio válida: " + fechaDeInicioElector);

            // Verificar si ya existe un domicilio con las características exactas
            System.out.println("Buscando domicilio con las características proporcionadas...");
            Domicilio domicilio = domicilioRepository
                    .findByEntidadFederativaIdAndMunicipioIdAndLocalidadIdAndColoniaIdAndCodigoPostalIdAndCalleAndNumeroExteriorAndNumeroInterior(
                            domicilioDTO.getEntidadFederativaId(),
                            domicilioDTO.getMunicipioId(),
                            domicilioDTO.getLocalidadId(),
                            domicilioDTO.getColoniaId(),
                            domicilioDTO.getCodigoPostalId(),
                            domicilioDTO.getCalle(),
                            domicilioDTO.getNumeroExterior(),
                            domicilioDTO.getNumeroInterior()
                    ).orElseGet(() -> {
                        System.out.println("No se encontró un domicilio existente. Creando uno nuevo...");
                        Domicilio nuevoDomicilio = new Domicilio();
                        nuevoDomicilio.setEntidadFederativa(
                                entidadFederativaRepository.findById(domicilioDTO.getEntidadFederativaId())
                                        .orElseThrow(() -> new EntityNotFoundException("Entidad Federativa no encontrada."))
                        );
                        nuevoDomicilio.setMunicipio(
                                municipioRepository.findById(domicilioDTO.getMunicipioId())
                                        .orElseThrow(() -> new EntityNotFoundException("Municipio no encontrado."))
                        );
                        nuevoDomicilio.setLocalidad(localidadRepository.findById(domicilioDTO.getLocalidadId()).orElse(null));
                        nuevoDomicilio.setColonia(coloniaRepository.findById(domicilioDTO.getColoniaId()).orElse(null));
                        nuevoDomicilio.setCodigoPostal(postalRepository.findById(domicilioDTO.getCodigoPostalId()).orElse(null));
                        nuevoDomicilio.setCalle(domicilioDTO.getCalle());
                        nuevoDomicilio.setNumeroExterior(domicilioDTO.getNumeroExterior());
                        nuevoDomicilio.setNumeroInterior(domicilioDTO.getNumeroInterior());
                        System.out.println("Nuevo domicilio configurado: " + nuevoDomicilio);
                        return domicilioRepository.save(nuevoDomicilio);
                    });
            System.out.println("Domicilio seleccionado o creado: " + domicilio);

            // Eliminar cualquier relación existente de tipo 2 para evitar duplicados
            System.out.println("Verificando si existe una relación PersonaDomicilio de tipo 2...");
            Optional<PersonaDomicilio> relacionExistente = personaDomicilioRepository
                    .findByPersonaIdAndTipoDeDomicilioId(idPersona, 2L);

            if (relacionExistente.isPresent()) {
                System.out.println("Relación de tipo 2 encontrada. Eliminándola...");
                personaDomicilioRepository.delete(relacionExistente.get());
                System.out.println("Relación eliminada.");
            } else {
                System.out.println("No se encontró ninguna relación de tipo 2.");
            }

            System.out.println("Creando nueva relación PersonaDomicilio...");
            PersonaDomicilio nuevaRelacion = new PersonaDomicilio();
            PersonaDomicilioId idCompuesto = new PersonaDomicilioId(idPersona, domicilio.getId());
            nuevaRelacion.setId(idCompuesto);
            nuevaRelacion.setPersona(persona);
            nuevaRelacion.setDomicilio(domicilio);
            nuevaRelacion.setTipoDeDomicilio(
                    tipoDeDomicilioRepository.findById(2L)
                            .orElseThrow(() -> new EntityNotFoundException("Tipo de domicilio no encontrado."))
            );
            nuevaRelacion.setFechaDeInicio(fechaActual); // Usar la fecha actual como fecha de inicio
            personaDomicilioRepository.save(nuevaRelacion);
            System.out.println("Nueva relación PersonaDomicilio creada: " + nuevaRelacion);


            // Crear un nuevo elector asociado a la persona
            System.out.println("Creando un nuevo elector asociado a la persona...");
            Elector nuevoElector = new Elector();
            nuevoElector.setPersona(persona);
            nuevoElector.setFechaDeInicio(fechaDeInicioElector);
            electorRepository.save(nuevoElector);
            System.out.println("Elector registrado exitosamente: " + nuevoElector);

            return nuevoElector;
        } catch (Exception e) {
            System.out.println("Error durante el registro del elector: " + e.getMessage());
            throw e; // Re-lanzar la excepción para ser manejada en capas superiores
        }
    }

    public Optional<Elector> buscarElectorPorPersona(Long idPersona) {
        System.out.println("Validando si ya existe un elector asociado a la persona con ID proporcionado:");

        // Log de los datos para verificar
        System.out.println("ID de Persona: " + idPersona);

        return electorRepository.findByPersonaId(idPersona);
    }

    
    
    //ELIMINAR ELECTOR

    @Transactional
    public void eliminarElector(Long idDeElector) {
        // Verificar si el elector existe
        Optional<Elector> electorOpt = electorRepository.findById(idDeElector);
        if (electorOpt.isEmpty()) {
            throw new EntityNotFoundException("El elector con ID " + idDeElector + " no existe.");
        }

        // Verificar dependencias críticas
        List<String> dependenciasCriticas = verificarDependenciasCriticasElector(idDeElector);
        if (!dependenciasCriticas.isEmpty()) {
            throw new IllegalStateException("No se puede eliminar el elector. Relacionado con: " +
                    String.join(", ", dependenciasCriticas));
        }

        // Eliminar el elector
        electorRepository.deleteById(idDeElector);
        System.out.println("Elector eliminado exitosamente: ID " + idDeElector);
    }

    private List<String> verificarDependenciasCriticasElector(Long idDeElector) {
        List<String> tablasCriticas = new ArrayList<>();

        // Verificar relaciones en ElectorInstancia
        if (electorInstanciaRepository.existsByElectorId(idDeElector)) {
            tablasCriticas.add("ElectorInstancia");
        }

        // Verificar relaciones en ElectorCandidatura
        if (electorCandidaturaRepository.existsByElectorId(idDeElector)) {
            tablasCriticas.add("ElectorCandidatura");
        }

        return tablasCriticas;
    }



}