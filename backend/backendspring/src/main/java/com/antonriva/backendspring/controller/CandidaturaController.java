package com.antonriva.backendspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.antonriva.backendspring.dto.CandidaturaElectorDetalleDTO;
import com.antonriva.backendspring.service.CandidaturaService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/candidatura")
@CrossOrigin(origins="http://localhost:5173")
public class CandidaturaController {
	
	
	private final CandidaturaService candidaturaService;
	
	public CandidaturaController(CandidaturaService candidaturaService) {
		this.candidaturaService = candidaturaService;
		
	}
	
    @GetMapping("/elector/{electorId}")
    public ResponseEntity<?> getDetalleDeCandidaturaElector(@PathVariable Long electorId) {
        System.out.println("Recibida solicitud para el elector con ID: " + electorId);
        try {
            System.out.println("Llamando al servicio para obtener detalles de candidaturas...");
            List<CandidaturaElectorDetalleDTO> detalles = candidaturaService.getDetalleDeCandidaturaElector(electorId);
            System.out.println("Detalles obtenidos: " + detalles);
            return ResponseEntity.ok(detalles);
        } catch (IllegalArgumentException e) {
            System.out.println("Error de argumento: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID del elector proporcionado no es válido.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al procesar la solicitud.");
        }
    }
}


