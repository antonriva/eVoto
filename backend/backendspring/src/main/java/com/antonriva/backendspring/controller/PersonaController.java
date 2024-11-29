package com.antonriva.backendspring.controller;

import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.model.PersonaDomicilio;
import com.antonriva.backendspring.service.PersonaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

@RequestMapping("/api/persona/")
@RestController
@CrossOrigin(origins="http://localhost:5173")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    // Obtener todas las personas
    @GetMapping
    public ResponseEntity<List<Persona>> obtenerTodasLasPersonas() {
        List<Persona> personas = personaService.obtenerTodasLasPersonas();
        return ResponseEntity.ok(personas);
    }

    // Obtener una persona por ID
    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable int id) {
        return personaService.obtenerPersonaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar personas con filtros
    @GetMapping("/buscar")
    public ResponseEntity<List<Persona>> buscarConFiltros(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido,
            @RequestParam(required = false) String fechaNacimiento) {
        List<Persona> personas = personaService.buscarConFiltros(id, nombre, apellido, fechaNacimiento);
        return ResponseEntity.ok(personas);
    }
    
    @GetMapping("/{personaId}/domicilios")
    public ResponseEntity<?> obtenerPersonaConDomicilios(@PathVariable Integer personaId) {
        Optional<Persona> persona = personaService.obtenerPersonaConDomicilios(personaId);
        return persona.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    



    // Asignar un domicilio a una persona
    @PostMapping("/{personaId}/domicilio/{domicilioId}")
    public ResponseEntity<?> asignarDomicilioAPersona(
        @PathVariable Integer personaId,
        @PathVariable Integer domicilioId,
        @RequestParam(required = false) LocalDate fechaDeInicio
    ) {
        PersonaDomicilio personaDomicilio = personaService.asignarDomicilioAPersona(personaId, domicilioId, fechaDeInicio);
        return ResponseEntity.ok(personaDomicilio);
    }

    // Crear una persona
    @PostMapping
    public ResponseEntity<Persona> crearPersona(@Validated @RequestBody Persona nuevaPersona) {
        Persona personaGuardada = personaService.registrarPersona(nuevaPersona);
        return ResponseEntity.status(HttpStatus.CREATED).body(personaGuardada);
    }

    // Actualizar una persona
    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizarPersona(@PathVariable int id, @RequestBody Persona nuevaPersona) {
        try {
            Persona personaActualizada = personaService.actualizarPersona(id, nuevaPersona);
            return ResponseEntity.ok(personaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
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
}
