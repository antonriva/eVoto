package com.antonriva.backendspring.controller;

import com.antonriva.backendspring.dto.DetalleDomicilioDTO;
import com.antonriva.backendspring.dto.PersonaBuscarCompletoDTO;
import com.antonriva.backendspring.dto.PersonaEditarDTO;
import com.antonriva.backendspring.dto.PersonaRegistroDTO;
import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.model.PersonaDomicilio;
import com.antonriva.backendspring.service.PersonaDomicilioService;
import com.antonriva.backendspring.service.PersonaService;
import com.antonriva.backendspring.repository.PersonaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RequestMapping("/api/persona")
@RestController
public class PersonaController {


    private final PersonaService personaService;
    private final PersonaDomicilioService personaDomicilioService;


    public PersonaController(PersonaService personaService, PersonaDomicilioService personaDomicilioService) {
        this.personaService = personaService;
        this.personaDomicilioService = personaDomicilioService;
    }
    
//PAGINA DE BUSQUEDA
    
  //------------------// BUSCAR CON PARAMETROS, DEVUELVE BUSQUEDA COMPLETA
    
    @GetMapping("/buscar")
    public ResponseEntity<List<PersonaBuscarCompletoDTO>> buscarPersonas(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellidoPaterno,
            @RequestParam(required = false) String apellidoMaterno,
            @RequestParam(required = false) String anioNacimiento,
            @RequestParam(required = false) String mesNacimiento,
            @RequestParam(required = false) String diaNacimiento,
            @RequestParam(required = false) String anioFin,
            @RequestParam(required = false) String mesFin,
            @RequestParam(required = false) String diaFin,
            @RequestParam(required = false) String entidadFederativa,
            @RequestParam(required = false) String municipio,
            @RequestParam(required = false) String localidad,
            @RequestParam(required = false) String colonia,
            @RequestParam(required = false) String codigoPostal,
            @RequestParam(required = false) String tipoDeDomicilio
    ) {
        try {
            // Limpiar los valores de los parámetros
        	
            Long idParsed = parseLongOrNull(id);
            Integer anioNacimientoParsed = parseIntegerOrNull(anioNacimiento);
            Integer mesNacimientoParsed = parseIntegerOrNull(mesNacimiento);
            Integer diaNacimientoParsed = parseIntegerOrNull(diaNacimiento);
            Integer anioFinParsed = parseIntegerOrNull(anioFin);
            Integer mesFinParsed = parseIntegerOrNull(mesFin);
            Integer diaFinParsed = parseIntegerOrNull(diaFin);
            Long entidadFederativaParsed = parseLongOrNull(entidadFederativa);
            Long municipioParsed = parseLongOrNull(municipio);
            Long localidadParsed = parseLongOrNull(localidad);
            Long coloniaParsed = parseLongOrNull(colonia);
            Long codigoPostalParsed = parseLongOrNull(codigoPostal);
            Long tipoDeDomicilioParsed = parseLongOrNull(tipoDeDomicilio);

            // Llamar al servicio para realizar la búsqueda
            List<PersonaBuscarCompletoDTO> resultados = personaService.buscarPersonasConDetalles(
                    idParsed,
                    nombre,
                    apellidoPaterno,
                    apellidoMaterno,
                    anioNacimientoParsed,
                    mesNacimientoParsed,
                    diaNacimientoParsed,
                    anioFinParsed,
                    mesFinParsed,
                    diaFinParsed,
                    entidadFederativaParsed,
                    municipioParsed,
                    localidadParsed,
                    coloniaParsed,
                    codigoPostalParsed,
                    tipoDeDomicilioParsed
            );

            // Retornar la lista de DTOs
            return ResponseEntity.ok(resultados);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al buscar personas con detalles. Por favor intente nuevamente.", e);
        }
    }

    // Métodos auxiliares para convertir valores
    private Long parseLongOrNull(String value) {
        try {
            return (value != null && !"null".equals(value) && !value.isEmpty()) ? Long.parseLong(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer parseIntegerOrNull(String value) {
        try {
            return (value != null && !"null".equals(value) && !value.isEmpty()) ? Integer.parseInt(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    //BUSCAR ELEMENTOS ASOCIADOS EN UNA TABLA CON RELACION INTERMEDIA
    
    @GetMapping("/{idPersona}/detalles-domicilios")
    public ResponseEntity<List<DetalleDomicilioDTO>> obtenerDomiciliosDetalle(@PathVariable Long idPersona) {
        List<DetalleDomicilioDTO> detalles = personaDomicilioService.obtenerDomiciliosPorPersona(idPersona);
        return ResponseEntity.ok(detalles);
    }

    
    
    //ESTE NO LO ESTAMOS USANDO, PERO EN CASO DE SER REQUERIDO. PROBABLEMENTE ELIMINEMOS LA RELACION CON PADR
    @GetMapping("/{id}/padres")
    public ResponseEntity<Map<String, PersonaBuscarCompletoDTO>> obtenerPadres(@PathVariable Long id) {
        try {
            Map<String,PersonaBuscarCompletoDTO> padres = personaService.obtenerPadres(id);
            return ResponseEntity.ok(padres);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    

    
 //------------------//EN EL FRONTEND, TENDREMOS SELECCION DE EDITAR EN LA PAGINA DE BUSQUEDA. AQUI VIENE LO QUE UTILIZAMOS PARA EDITAR O UPDATE  
    
    //OBTENEMOS TODOS LOS DATOS PARA QUE APAREZCAN COMO DEFAULT EN LOS CAMPOS, GET
    
    @GetMapping("/{id}/editar")
    public ResponseEntity<?> obtenerDatosParaEdicion(@PathVariable Long id) {
        try {
            PersonaEditarDTO personaEditarDTO = personaService.obtenerDatosParaEdicion(id);
            return ResponseEntity.ok(personaEditarDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los datos para edición.");
        }
    }
    
    //HACEMOS LA EDICION CON LOS DATOS QUE QUEREMOS, PUT
    
    @PutMapping("/{id}/editar")
    public ResponseEntity<?> editarPersona(
            @PathVariable Long id,
            @RequestBody @Valid PersonaEditarDTO personaEditarDTO) {
        try {
            personaService.editarPersona(id, personaEditarDTO);
            return ResponseEntity.ok("Persona actualizada exitosamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la persona.");
        }
    }

 
    
  //------------------//ELIMINACION DE UNA PERSONA
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPersona(@PathVariable Long id) {
        try {
            personaService.eliminarPersona(id);
            return ResponseEntity.ok("Persona eliminada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la persona: " + e.getMessage());
        }
    }
    
    
    
//PAGINA DE REGISTRO DE LA PERSONA
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPersona(@RequestBody @Valid PersonaEditarDTO personaRegistrarDTO, 
                                              @RequestParam(required = false) boolean confirmar) {
        try {
            // Verificar si ya existe una persona con las mismas características
            Optional<Persona> personaExistente = personaService.buscarPersonaExacta(personaRegistrarDTO);

            if (personaExistente.isPresent() && !confirmar) {
                Persona persona = personaExistente.get();
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        "Ya existe una persona con estas características: ID " + persona.getId() + 
                        ". Si desea continuar, añada el parámetro `confirmar=true`."
                );
            }

            // Registrar nueva persona
            Persona nuevaPersona = personaService.registrarPersona(personaRegistrarDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    "Persona registrada exitosamente con ID: " + nuevaPersona.getId()
            );

        } catch (IllegalArgumentException e) {
            // Manejo de errores de validación
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            // Manejo de entidades no encontradas
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // Manejo de errores internos
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar la persona.");
        }
    }
 
}


    
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
