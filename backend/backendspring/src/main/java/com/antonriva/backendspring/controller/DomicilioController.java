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
@CrossOrigin(origins = "http://localhost:5173")
public class DomicilioController {
	/*

    @Autowired
    private DomicilioService domicilioService;

    // Obtener todos los domicilios
    //SI SIRVE
    @GetMapping
    public ResponseEntity<List<Domicilio>> obtenerTodosLosDomicilios() {
        List<Domicilio> domicilios = domicilioService.obtenerTodosLosDomicilios();
        return ResponseEntity.ok(domicilios);
    }

    // Obtener un domicilio por ID
    //SI SIRVE
    @GetMapping("/{id}")
    public ResponseEntity<Domicilio> obtenerDomicilioPorId(@PathVariable int id) {
        return domicilioService.obtenerDomicilioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener domicilios asociados a una persona
    //SI FUNCIONA
    @GetMapping("/persona/{personaId}")
    public ResponseEntity<List<Domicilio>> obtenerDomiciliosPorPersona(@PathVariable int personaId) {
        Persona persona = new Persona();
        persona.setId(personaId);
        List<Domicilio> domicilios = domicilioService.obtenerDomiciliosPorPersona(persona);
        return ResponseEntity.ok(domicilios);
    }

    // Buscar domicilios con filtros
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
        validarDatosDomicilio(nuevoDomicilio);
        Domicilio domicilioGuardado = domicilioService.registrarDomicilio(nuevoDomicilio);
        return ResponseEntity.status(HttpStatus.CREATED).body(domicilioGuardado);
    }

    // Actualizar un domicilio
    @PutMapping("/{id}")
    public ResponseEntity<Domicilio> actualizarDomicilio(@PathVariable int id, @RequestBody Domicilio nuevoDomicilio) {
        try {
            validarDatosDomicilio(nuevoDomicilio);
            Domicilio domicilioActualizado = domicilioService.actualizarDomicilio(id, nuevoDomicilio);
            return ResponseEntity.ok(domicilioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
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

    // Método de validación
    private void validarDatosDomicilio(Domicilio domicilio) {
        if (domicilio.getCalle() == null || domicilio.getCalle().isBlank() || !domicilio.getCalle().matches("^[A-ZÁÉÍÓÚÑ ]+$")) {
            throw new IllegalArgumentException("La calle debe estar en mayúsculas y no puede contener caracteres especiales o estar vacía.");
        }
        if (domicilio.getNumeroExterior() == null || domicilio.getNumeroExterior() <= 0) {
            throw new IllegalArgumentException("El número exterior debe ser un entero positivo.");
        }
        if (domicilio.getColonia() == null) {
            throw new IllegalArgumentException("El campo 'colonia' es obligatorio.");
        }
        if (domicilio.getMunicipio() == null) {
            throw new IllegalArgumentException("El campo 'municipio' es obligatorio.");
        }
        if (domicilio.getEntidadFederativa() == null) {
            throw new IllegalArgumentException("El campo 'entidadFederativa' es obligatorio.");
        }
    }
    */
}

