package com.antonriva.backendspring.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antonriva.backendspring.model.Localidad;
import com.antonriva.backendspring.service.LocalidadService;


@RestController
@RequestMapping("/api/localidad")
@CrossOrigin(origins="http://localhost:5173")
public class LocalidadController {

    private final LocalidadService localidadService;
    
    public LocalidadController(LocalidadService localidadService) {
    	this.localidadService = localidadService;
    }
    
    // Buscar municipios por ID de entidad federativa
    @GetMapping("/municipio/{municipioId}")
    public ResponseEntity<List<Localidad>> obtenerLocalidadesPorMunicipioId(@PathVariable Long municipioId) {
        List<Localidad> localidades = localidadService.obtenerLocalidadesPorMunicipioId(municipioId);
        return ResponseEntity.ok(localidades);
    }
}
