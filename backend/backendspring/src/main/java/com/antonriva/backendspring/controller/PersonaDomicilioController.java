package com.antonriva.backendspring.controller;

import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.model.Domicilio;
import com.antonriva.backendspring.model.PersonaDomicilio;
import com.antonriva.backendspring.service.PersonaDomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/persona-domicilio")
@CrossOrigin(origins="http://localhost:5173")
public class PersonaDomicilioController {

    @Autowired
    private PersonaDomicilioService personaDomicilioService;

    // Crear una nueva relación
    @PostMapping
    public ResponseEntity<PersonaDomicilio> crearRelacion(
            @RequestParam Integer idPersona,
            @RequestParam Integer idDomicilio,
            @RequestParam String fechaDeInicio) {
        Persona persona = new Persona();
        persona.setId(idPersona);

        Domicilio domicilio = new Domicilio();
        domicilio.setId(idDomicilio);

        LocalDate fechaInicio = LocalDate.parse(fechaDeInicio);
        PersonaDomicilio relacion = personaDomicilioService.crearRelacion(persona, domicilio, fechaInicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(relacion);
    }

    // Obtener todas las relaciones
    @GetMapping
    public ResponseEntity<List<PersonaDomicilio>> obtenerTodasLasRelaciones() {
        List<PersonaDomicilio> relaciones = personaDomicilioService.obtenerTodasLasRelaciones();
        return ResponseEntity.ok(relaciones);
    }

    // Obtener relaciones de una persona
    @GetMapping("/persona/{idPersona}")
    public ResponseEntity<List<PersonaDomicilio>> obtenerRelacionesPorPersona(@PathVariable Integer idPersona) {
        Persona persona = new Persona();
        persona.setId(idPersona);

        List<PersonaDomicilio> relaciones = personaDomicilioService.obtenerRelacionesPorPersona(persona);
        return ResponseEntity.ok(relaciones);
    }

    // Obtener relaciones de un domicilio
    @GetMapping("/domicilio/{idDomicilio}")
    public ResponseEntity<List<PersonaDomicilio>> obtenerRelacionesPorDomicilio(@PathVariable Integer idDomicilio) {
        Domicilio domicilio = new Domicilio();
        domicilio.setId(idDomicilio);

        List<PersonaDomicilio> relaciones = personaDomicilioService.obtenerRelacionesPorDomicilio(domicilio);
        return ResponseEntity.ok(relaciones);
    }

    // Eliminar una relación
    @DeleteMapping
    public ResponseEntity<Void> eliminarRelacion(
            @RequestParam Integer idPersona,
            @RequestParam Integer idDomicilio) {
        Persona persona = new Persona();
        persona.setId(idPersona);

        Domicilio domicilio = new Domicilio();
        domicilio.setId(idDomicilio);

        personaDomicilioService.eliminarRelacion(persona, domicilio);
        return ResponseEntity.noContent().build();
    }

    // Actualizar fecha de fin
    @PutMapping("/fecha-fin")
    public ResponseEntity<PersonaDomicilio> actualizarFechaDeFin(
            @RequestParam Integer idPersona,
            @RequestParam Integer idDomicilio,
            @RequestParam String fechaDeFin) {
        Persona persona = new Persona();
        persona.setId(idPersona);

        Domicilio domicilio = new Domicilio();
        domicilio.setId(idDomicilio);

        LocalDate fechaFin = LocalDate.parse(fechaDeFin);
        PersonaDomicilio actualizada = personaDomicilioService.actualizarFechaDeFin(persona, domicilio, fechaFin);
        return ResponseEntity.ok(actualizada);
    }
}
