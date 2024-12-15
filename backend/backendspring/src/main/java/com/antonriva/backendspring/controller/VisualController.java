package com.antonriva.backendspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.antonriva.backendspring.model.Visual;
import com.antonriva.backendspring.service.VisualService;

import jakarta.persistence.EntityNotFoundException;

@RequestMapping("/api/visual")
@RestController
@CrossOrigin(origins="http://localhost:5173")
public class VisualController {
	
	private final VisualService visualService;
	
	public VisualController(VisualService visualService) {
		this.visualService = visualService;
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarLogo(
	        @RequestParam("logoFile") MultipartFile logoFile,
	        @RequestParam(required = false) Long partidoId,
	        @RequestParam Long tipoDeVisualId,
	        @RequestParam Long recursoVigenteId) {
	    try {
	        // Imprimir los valores recibidos
	        System.out.println("Valores recibidos en el controlador:");
	        System.out.println("Archivo: " + (logoFile != null ? logoFile.getOriginalFilename() : "null"));
	        System.out.println("Partido ID: " + partidoId);
	        System.out.println("Tipo de Visual ID: " + tipoDeVisualId);
	        System.out.println("Recurso Vigente ID: " + recursoVigenteId);

	        // Llamar al servicio para registrar el logo
	        Visual visual = visualService.registrarLogo(logoFile, partidoId, tipoDeVisualId, recursoVigenteId);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Logo registrado exitosamente con ID: " + visual.getId());
	    } catch (IllegalArgumentException e) {
	        System.out.println("Error de validación: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    } catch (EntityNotFoundException e) {
	        System.out.println("Entidad no encontrada: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (Exception e) {
	        System.out.println("Error interno: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el logo.");
	    }
	}


}
