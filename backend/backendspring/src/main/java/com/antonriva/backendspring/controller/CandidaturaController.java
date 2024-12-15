package com.antonriva.backendspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antonriva.backendspring.dto.CandidaturaDetalleDTO;
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
	
    @GetMapping("/{idDeElector}")
    public ResponseEntity<?> obtenerCandidaturasPorElector(@PathVariable Long idDeElector) {
        try {
            List<CandidaturaDetalleDTO> candidaturas = candidaturaService.obtenerCandidaturasPorElector(idDeElector);
            return ResponseEntity.ok(candidaturas);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las candidaturas para el elector con ID " + idDeElector + ".");
        }
    }

}
