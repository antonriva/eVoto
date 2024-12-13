package com.antonriva.backendspring.controller;

import com.antonriva.backendspring.model.Municipio;
import com.antonriva.backendspring.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/municipio")
@CrossOrigin(origins="http://localhost:5173")
public class MunicipioController {
	
    
    private final MunicipioService municipioService;
    
    public MunicipioController(MunicipioService municipioService) {
    	this.municipioService = municipioService;
    }
    
    // Buscar municipios por ID de entidad federativa
    @GetMapping("/entidad/{entidadFederativaId}")
    public ResponseEntity<List<Municipio>> obtenerMunicipiosPorEntidadFederativaId(@PathVariable Long entidadFederativaId) {
        List<Municipio> municipios = municipioService.obtenerMunicipiosPorEntidadFederativaId(entidadFederativaId);
        return ResponseEntity.ok(municipios);
    }

	/*



    // Obtener todos los municipios
    @GetMapping
    public ResponseEntity<List<Municipio>> obtenerTodosLosMunicipios() {
        List<Municipio> municipios = municipioService.obtenerTodosLosMunicipios();
        return ResponseEntity.ok(municipios);
    }

    // Obtener un municipio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Municipio> obtenerMunicipioPorId(@PathVariable int id) {
        Optional<Municipio> municipio = municipioService.obtenerMunicipioPorId(id);
        return municipio.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    // Buscar municipios con filtros
    @GetMapping("/buscar")
    public ResponseEntity<List<Municipio>> buscarConFiltros(
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) Integer entidadFederativaId) {
        List<Municipio> municipios = municipioService.buscarConFiltros(descripcion, entidadFederativaId);
        return ResponseEntity.ok(municipios);
    }

    // Crear un nuevo municipio
    @PostMapping
    public ResponseEntity<Municipio> crearMunicipio(@RequestBody Municipio nuevoMunicipio) {
        Municipio municipioGuardado = municipioService.registrarMunicipio(nuevoMunicipio);
        return ResponseEntity.status(HttpStatus.CREATED).body(municipioGuardado);
    }

    // Actualizar un municipio
    @PutMapping("/{id}")
    public ResponseEntity<Municipio> actualizarMunicipio(@PathVariable int id, @RequestBody Municipio nuevoMunicipio) {
        try {
            Municipio municipioActualizado = municipioService.actualizarMunicipio(id, nuevoMunicipio);
            return ResponseEntity.ok(municipioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un municipio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMunicipio(@PathVariable int id) {
        try {
            municipioService.eliminarMunicipio(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    */
}

