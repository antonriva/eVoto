package com.antonriva.backendspring.controller;

import com.antonriva.backendspring.model.Colonia;
import com.antonriva.backendspring.model.Localidad;
import com.antonriva.backendspring.service.ColoniaService;
import com.antonriva.backendspring.service.LocalidadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/colonia")
public class ColoniaController {
	
    private final ColoniaService coloniaService;
    
    public ColoniaController(ColoniaService coloniaService) {
    	this.coloniaService = coloniaService;
    }
    
    // Buscar municipios por ID de entidad federativa
    @GetMapping("/municipio/{municipioId}")
    public ResponseEntity<List<Colonia>> obtenerLocalidadesPorMunicipioId(@PathVariable Long municipioId) {
        List<Colonia> colonias = coloniaService.obtenerColoniasPorMunicipioId(municipioId);
        return ResponseEntity.ok(colonias);
    }
	
	
	/*

    @Autowired
    private ColoniaService coloniaService;

    // Obtener todas las colonias
    @GetMapping
    public ResponseEntity<List<Colonia>> obtenerTodasLasColonias() {
        List<Colonia> colonias = coloniaService.obtenerTodasLasColonias();
        return ResponseEntity.ok(colonias);
    }

    // Obtener una colonia por ID
    @GetMapping("/{id}")
    public ResponseEntity<Colonia> obtenerColoniaPorId(@PathVariable int id) {
        Optional<Colonia> colonia = coloniaService.obtenerColoniaPorId(id);
        return colonia.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Buscar colonias por ID de municipio
    @GetMapping("/municipio/{municipioId}")
    public ResponseEntity<List<Colonia>> obtenerColoniasPorMunicipioId(@PathVariable int municipioId) {
        List<Colonia> colonias = coloniaService.obtenerColoniasPorMunicipioId(municipioId);
        return ResponseEntity.ok(colonias);
    }

    // Buscar colonias con filtros
    @GetMapping("/buscar")
    public ResponseEntity<List<Colonia>> buscarConFiltros(
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) Integer municipioId) {
        List<Colonia> colonias = coloniaService.buscarConFiltros(descripcion, municipioId);
        return ResponseEntity.ok(colonias);
    }

    // Crear una nueva colonia
    @PostMapping
    public ResponseEntity<Colonia> crearColonia(@RequestBody Colonia nuevaColonia) {
        Colonia coloniaGuardada = coloniaService.registrarColonia(nuevaColonia);
        return ResponseEntity.status(HttpStatus.CREATED).body(coloniaGuardada);
    }

    // Actualizar una colonia
    @PutMapping("/{id}")
    public ResponseEntity<Colonia> actualizarColonia(@PathVariable int id, @RequestBody Colonia nuevaColonia) {
        try {
            Colonia coloniaActualizada = coloniaService.actualizarColonia(id, nuevaColonia);
            return ResponseEntity.ok(coloniaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una colonia
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarColonia(@PathVariable int id) {
        try {
            coloniaService.eliminarColonia(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    */
}
