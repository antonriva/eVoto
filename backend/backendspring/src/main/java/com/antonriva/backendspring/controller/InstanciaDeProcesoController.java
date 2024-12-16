package com.antonriva.backendspring.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antonriva.backendspring.dto.InstanciaDeProcesoDetalleDTO;
import com.antonriva.backendspring.dto.InstanciaEditarDTO;
import com.antonriva.backendspring.service.InstanciaDeProcesoService;

import jakarta.persistence.EntityNotFoundException;

@RequestMapping("/api/instancia")
@RestController
@CrossOrigin(origins="http://localhost:5173")
public class InstanciaDeProcesoController {
	

    
    private final InstanciaDeProcesoService instanciaDeProcesoService;
    
    public InstanciaDeProcesoController(InstanciaDeProcesoService instanciaDeProcesoService) {
    	this.instanciaDeProcesoService=instanciaDeProcesoService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarInstanciasConFiltros(
            @RequestParam(required = false) Long idDeInstanciaDeProceso,
            @RequestParam(required = false) Long idNivel,
            @RequestParam(required = false) Long idProceso,
            @RequestParam(required = false) Long idEntidadFederativa,
            @RequestParam(required = false) Long idMunicipio,
            @RequestParam(required = false) Long idLocalidad,
            @RequestParam(required = false) Integer anioInicio,
            @RequestParam(required = false) Integer mesInicio,
            @RequestParam(required = false) Integer diaInicio,
            @RequestParam(required = false) Integer anioFin,
            @RequestParam(required = false) Integer mesFin,
            @RequestParam(required = false) Integer diaFin
    ) {
        try {
            List<InstanciaDeProcesoDetalleDTO> resultados = instanciaDeProcesoService.buscarInstanciasConFiltros(
                    idDeInstanciaDeProceso, idNivel, idProceso, idEntidadFederativa, idMunicipio, idLocalidad,
                    anioInicio, mesInicio, diaInicio,
                    anioFin, mesFin, diaFin
            );
            return ResponseEntity.ok(resultados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }
    
    @GetMapping("/editar/{idDeInstancia}")
    public ResponseEntity<?> obtenerDatosParaEdicion(@PathVariable Long idDeInstancia) {
        try {
            InstanciaEditarDTO datos = instanciaDeProcesoService.obtenerDatosParaEdicion(idDeInstancia);
            return ResponseEntity.ok(datos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }

    @PutMapping("/editar/{idDeInstancia}")
    public ResponseEntity<?> editarInstancia(@PathVariable Long idDeInstancia, @RequestBody InstanciaEditarDTO dto) {
        try {
            instanciaDeProcesoService.editarInstancia(idDeInstancia, dto);
            return ResponseEntity.ok("Instancia de proceso actualizada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }
    
    
    @PostMapping("/crear")
    public ResponseEntity<?> registrarNuevaInstancia(@RequestBody InstanciaEditarDTO dto) {
        try {
            Long idNuevaInstancia = instanciaDeProcesoService.registrarNuevaInstancia(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Instancia de proceso creada con ID: " + idNuevaInstancia);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }

}
