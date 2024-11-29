package com.antonriva.backendspring.controller;

import com.antonriva.backendspring.model.Domicilio;
import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/domicilio")
@CrossOrigin(origins="http://localhost:5173")
public class DomicilioController {


    @Autowired
    private DomicilioService domicilioService;

    // Obtener todos los domicilios
    @GetMapping
    public ResponseEntity<List<Domicilio>> obtenerTodosLosDomicilios() {
        List<Domicilio> domicilios = domicilioService.obtenerTodosLosDomicilios();
        return ResponseEntity.ok(domicilios);
    }

    // Obtener un domicilio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Domicilio> obtenerDomicilioPorId(@PathVariable int id) {
        Optional<Domicilio> domicilio = domicilioService.obtenerDomicilioPorId(id);
        return domicilio.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Obtener domicilios asociados a una persona
    @GetMapping("/persona/{personaId}")
    public ResponseEntity<List<Domicilio>> obtenerDomiciliosPorPersona(@PathVariable int personaId) {
        // Crear una instancia básica de Persona con el ID proporcionado
        Persona persona = new Persona();
        persona.setId(personaId);

        // Obtener los domicilios asociados a la persona
        List<Domicilio> domicilios = domicilioService.obtenerDomiciliosPorPersona(persona);

        return ResponseEntity.ok(domicilios);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<Domicilio>> buscarConFiltros(
            @RequestParam(required = false) String calle,
            @RequestParam(required = false) Integer numeroExterior,
            @RequestParam(required = false) String municipio,
            @RequestParam(required = false) String colonia) {
        List<Domicilio> domicilios = domicilioService.buscarConFiltros(calle, numeroExterior, municipio, colonia);
        return ResponseEntity.ok(domicilios);
    }

    // Crear un nuevo domicilio
    @PostMapping
    public ResponseEntity<Domicilio> crearDomicilio(@RequestBody Domicilio nuevoDomicilio) {
        Domicilio domicilioGuardado = domicilioService.registrarDomicilio(nuevoDomicilio);
        return ResponseEntity.status(HttpStatus.CREATED).body(domicilioGuardado);
    }

    // Actualizar un domicilio
    @PutMapping("/{id}")
    public ResponseEntity<Domicilio> actualizarDomicilio(@PathVariable int id, @RequestBody Domicilio nuevoDomicilio) {
        try {
            Domicilio domicilioActualizado = domicilioService.actualizarDomicilio(id, nuevoDomicilio);
            return ResponseEntity.ok(domicilioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un domicilio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDomicilio(@PathVariable int id) {
        try {
            domicilioService.eliminarDomicilio(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        
        
        
    }
}
