package com.antonriva.backendspring.controller;

import com.antonriva.backendspring.dto.DetalleDomicilioDTO;
import com.antonriva.backendspring.dto.PersonaBuscarCompletoDTO;
import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.model.PersonaDomicilio;
import com.antonriva.backendspring.service.PersonaDomicilioService;
import com.antonriva.backendspring.service.PersonaService;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
@CrossOrigin(origins="http://localhost:5173")
public class PersonaController {


    private final PersonaService personaService;
    private final PersonaDomicilioService personaDomicilioService;

    
    //GET
    
    public PersonaController(PersonaService personaService, PersonaDomicilioService personaDomicilioService) {
        this.personaService = personaService;
        this.personaDomicilioService = personaDomicilioService;
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<PersonaBuscarCompletoDTO>> buscarPersonas(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellidoPaterno,
            @RequestParam(required = false) String apellidoMaterno,
            @RequestParam(required = false) Integer anioNacimiento,
            @RequestParam(required = false) Integer mesNacimiento,
            @RequestParam(required = false) Integer diaNacimiento,
            @RequestParam(required = false) Integer anioFin,
            @RequestParam(required = false) Integer mesFin,
            @RequestParam(required = false) Integer diaFin,
            @RequestParam(required = false) Long entidadFederativa,
            @RequestParam(required = false) Long municipio,
            @RequestParam(required = false) Long localidad,
            @RequestParam(required = false) Long colonia,
            @RequestParam(required = false) Long codigoPostal,
            @RequestParam(required = false) Long tipoDeDomicilio
 
            
            
    ) {
        try {
            // Llamar al servicio para realizar la búsqueda
            List<PersonaBuscarCompletoDTO> resultados = personaService.buscarPersonasConDetalles(
            		id,
                    nombre,
                    apellidoPaterno,
                    apellidoMaterno,
                    anioNacimiento,
                    mesNacimiento,
                    diaNacimiento,
                    anioFin,
                    mesFin,
                    diaFin,
                    entidadFederativa,
                    municipio,
                    localidad, 
                    colonia, 
                    codigoPostal,
                    tipoDeDomicilio
            );

            // Retornar la lista de DTOs
            return ResponseEntity.ok(resultados);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al buscar personas con detalles. Por favor intente nuevamente.", e);
        }
    }
    
    @GetMapping("/{idPersona}/detalles-domicilios")
    public ResponseEntity<List<DetalleDomicilioDTO>> obtenerDomiciliosDetalle(@PathVariable Long idPersona) {
        List<DetalleDomicilioDTO> detalles = personaDomicilioService.obtenerDomiciliosPorPersona(idPersona);
        return ResponseEntity.ok(detalles);
    }

    
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
    
    
    //DELETE
    
    
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
}
    
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
/*





    // Asignar un domicilio a una persona
    //SI FUNCIONA, LA FECHA SI ES MAS QUE LA ACTUAL SE PONE LA DEL DIA ACTUAL
    @PostMapping("/{personaId}/domicilio/{domicilioId}")
    public ResponseEntity<?> asignarDomicilioAPersona(
        @PathVariable Integer personaId,
        @PathVariable Integer domicilioId,
        @RequestParam(value = "fechaDeInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDeInicio
    ) {
        // Ensure fechaDeInicio is provided and valid
        if (fechaDeInicio == null) {
            return ResponseEntity.badRequest().body("El parámetro 'fechaDeInicio' es obligatorio.");
        }

        // Validate that fechaDeInicio is not in the future
        if (fechaDeInicio.isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La fecha de inicio no puede ser posterior al día actual.");
        }

        PersonaDomicilio personaDomicilio = personaService.asignarDomicilioAPersona(personaId, domicilioId, fechaDeInicio);
        return ResponseEntity.ok(personaDomicilio);
    }


    // Crear una persona
    @PostMapping
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona nuevaPersona) {
        validarDatosPersona(nuevaPersona);
        Persona personaGuardada = personaService.registrarPersona(nuevaPersona);
        return ResponseEntity.status(HttpStatus.CREATED).body(personaGuardada);
    }

    // Actualizar una persona
 // Actualizar una persona
    //SI FUNCIONA TODOS LOS DATOS DEBEN PASARSE PARA QUE FUNCIONE
    @PutMapping("/{id}")w
    public ResponseEntity<Persona> actualizarPersona(@PathVariable int id, @RequestBody Persona nuevaPersona) {
        try {
            validarDatosPersona(nuevaPersona); // Apply restrictions on incoming data
            Persona personaActualizada = personaService.actualizarPersona(id, nuevaPersona);
            return ResponseEntity.ok(personaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Return 404 if the person is not found
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // Return 400 for validation or other errors
        }
    }


    // Eliminar una persona
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Integer id) {
        try {
            personaService.eliminarPersona(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Método de validación
    private void validarDatosPersona(Persona persona) {
        if (persona.getNombre() == null || persona.getNombre().isBlank() || !persona.getNombre().matches("^[A-ZÁÉÍÓÚÑ]+( [A-ZÁÉÍÓÚÑ]+)?$")) {
            throw new IllegalArgumentException("El nombre debe estar en mayúsculas, no contener números ni caracteres especiales, y no puede estar vacío.");
        }
        if (persona.getApellidoPaterno() == null || persona.getApellidoPaterno().isBlank() || !persona.getApellidoPaterno().matches("^[A-ZÁÉÍÓÚÑ]+$")) {
            throw new IllegalArgumentException("El apellido paterno debe estar en mayúsculas, no contener números ni caracteres especiales, y no puede estar vacío.");
        }
        if (persona.getApellidoMaterno() == null || persona.getApellidoMaterno().isBlank() || !persona.getApellidoMaterno().matches("^[A-ZÁÉÍÓÚÑ]+$")) {
            throw new IllegalArgumentException("El apellido materno debe estar en mayúsculas, no contener números ni caracteres especiales, y no puede estar vacío.");
        }
        if (persona.getFechaDeNacimiento() == null) {
            throw new IllegalArgumentException("El campo 'fechaDeNacimiento' es obligatorio y debe estar en el formato YYYY-MM-DD.");
        }
    }
    */
