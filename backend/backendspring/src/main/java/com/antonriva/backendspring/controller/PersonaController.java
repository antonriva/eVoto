package com.antonriva.backendspring.controller;

import com.antonriva.backendspring.dto.DetalleDomicilioDTO;
import com.antonriva.backendspring.dto.PersonaResumenDTO;
import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.model.PersonaDomicilio;
import com.antonriva.backendspring.service.PersonaDomicilioService;
import com.antonriva.backendspring.service.PersonaService;

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

    @Autowired
    private PersonaService personaService;
    
    @Autowired
    private PersonaDomicilioService personaDomicilioService;

    // Obtener todas las personas
    // SI FUNCIONA PRUEBA HTTPi
    //NOS DA TAMBIEN PERSONADOMICILIO
    @GetMapping
    public ResponseEntity<List<PersonaResumenDTO>> obtenerResumenDePersonas() {
        List<PersonaResumenDTO> resumenPersonas = personaService.obtenerResumenDePersonas();
        return ResponseEntity.ok(resumenPersonas);
    }
    
    // Endpoint para obtener los domicilios detallados de una persona
    @GetMapping("/{idPersona}/detalles-domicilios")
    public ResponseEntity<List<DetalleDomicilioDTO>> obtenerDomiciliosDetalle(@PathVariable int idPersona) {
        List<DetalleDomicilioDTO> detalles = personaDomicilioService.obtenerDomiciliosPorPersona(idPersona);
        return ResponseEntity.ok(detalles);
    }

    // Obtener una persona por ID
    //SI FUNCIONA NOS DA TAMBIEN PERSONA DOMICILIO
    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable int id) {
        return personaService.obtenerPersonaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //SI FUNCIONA
    @GetMapping("/buscar")
    public ResponseEntity<List<PersonaResumenDTO>> buscarConFiltros(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellidoPaterno,
            @RequestParam(required = false) String apellidoMaterno,
            @RequestParam(required = false) String fechaDeNacimiento,
            @RequestParam(required = false) String fechaDeFin,
            @RequestParam(required = false) Integer cantidadDomicilios) {

        List<PersonaResumenDTO> personas = personaService.buscarConFiltros(
            id, nombre, apellidoPaterno, apellidoMaterno, fechaDeNacimiento, fechaDeFin, cantidadDomicilios);

        return ResponseEntity.ok(personas);
    }


    
    //si funciona
    @GetMapping("/{personaId}/domicilios")
    public ResponseEntity<?> obtenerPersonaConDomicilios(@PathVariable Integer personaId) {
        Optional<Persona> persona = personaService.obtenerPersonaConDomicilios(personaId);
        return persona.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    




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
    @PutMapping("/{id}")
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
}
