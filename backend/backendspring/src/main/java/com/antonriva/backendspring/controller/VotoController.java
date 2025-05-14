package com.antonriva.backendspring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antonriva.backendspring.service.VotoService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/voto")
public class VotoController {
	
	
	private final VotoService votoService;
	
	public VotoController(VotoService votoService) {
		this.votoService = votoService;
	}
	
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarVoto(
	        @RequestParam Long idDeElector,
	        @RequestParam Long idDeInstanciaDeProceso,
	        @RequestParam Long idDeCandidatura) {
	    try {
	        votoService.registrarVoto(idDeElector, idDeInstanciaDeProceso, idDeCandidatura);
	        return ResponseEntity.ok("Voto registrado exitosamente.");
	    } catch (EntityNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
	    }

	


}}