package com.antonriva.backendspring.controller;

import com.antonriva.backendspring.model.EntidadFederativa;
import com.antonriva.backendspring.service.EntidadFederativaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entidad-federativa")
@CrossOrigin(origins="http://localhost:5173")
public class EntidadFederativaController {

    @Autowired
    private EntidadFederativaService entidadFederativaService;

    // Obtener todas las entidades federativas
    @GetMapping
    public ResponseEntity<List<EntidadFederativa>> obtenerTodasLasEntidades() {
        List<EntidadFederativa> entidades = entidadFederativaService.obtenerTodasLasEntidades();
        return ResponseEntity.ok(entidades);
    }

    // Obtener una entidad federativa por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntidadFederativa> obtenerEntidadPorId(@PathVariable int id) {
        Optional<EntidadFederativa> entidad = entidadFederativaService.obtenerEntidadPorId(id);
        return entidad.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Buscar entidades federativas con filtros
    @GetMapping("/buscar")
    public ResponseEntity<List<EntidadFederativa>> buscarConFiltros(
            @RequestParam(required = false) String descripcion) {
        List<EntidadFederativa> entidades = entidadFederativaService.buscarConFiltros(descripcion);
        return ResponseEntity.ok(entidades);
    }

    // Crear una nueva entidad federativa
    @PostMapping
    public ResponseEntity<EntidadFederativa> crearEntidad(@RequestBody EntidadFederativa nuevaEntidad) {
        EntidadFederativa entidadGuardada = entidadFederativaService.registrarEntidadFederativa(nuevaEntidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(entidadGuardada);
    }

    // Actualizar una entidad federativa
    @PutMapping("/{id}")
    public ResponseEntity<EntidadFederativa> actualizarEntidad(
            @PathVariable int id,
            @RequestBody EntidadFederativa nuevaEntidad) {
        try {
            EntidadFederativa entidadActualizada = entidadFederativaService.actualizarEntidad(id, nuevaEntidad);
            return ResponseEntity.ok(entidadActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una entidad federativa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntidad(@PathVariable int id) {
        try {
            entidadFederativaService.eliminarEntidad(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
