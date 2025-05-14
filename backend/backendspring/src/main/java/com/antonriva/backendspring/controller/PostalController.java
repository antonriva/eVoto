package com.antonriva.backendspring.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antonriva.backendspring.model.Postal;
import com.antonriva.backendspring.service.PostalService;


@RestController
@RequestMapping("/api/postal")
public class PostalController {
	
    private final PostalService postalService;
    
    public PostalController(PostalService postalService) {
    	this.postalService = postalService;
    }
    
    // Buscar municipios por ID de entidad federativa
    @GetMapping("/colonia/{coloniaId}")
    public ResponseEntity<List<Postal>> obtenerLocalidadesPorMunicipioId(@PathVariable Long coloniaId) {
        List<Postal> postales = postalService.obtenerPostalPorColoniaId(coloniaId);
        return ResponseEntity.ok(postales);
    }

}
