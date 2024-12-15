package com.antonriva.backendspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antonriva.backendspring.dto.PartidoBuscarDetalleDTO;
import com.antonriva.backendspring.model.Partido;
import com.antonriva.backendspring.service.PartidoService;

@RestController
@RequestMapping("/api/partido")
@CrossOrigin(origins="http://localhost:5173")
public class PartidoController {

    
    private final PartidoService partidoService;
    
    public PartidoController(PartidoService partidoService) {
    	this.partidoService = partidoService;
    	
    }


    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPartidosConDetalles(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String denominacion,
            @RequestParam(required = false) String siglas,
            @RequestParam(required = false) Integer anioInicio,
            @RequestParam(required = false) Integer mesInicio,
            @RequestParam(required = false) Integer diaInicio,
            @RequestParam(required = false) Integer anioFin,
            @RequestParam(required = false) Integer mesFin,
            @RequestParam(required = false) Integer diaFin
    ) {
        try {
            List<PartidoBuscarDetalleDTO> partidos = partidoService.buscarPartidosConDetalles(
                    id,
                    denominacion,
                    siglas,
                    anioInicio,
                    mesInicio,
                    diaInicio,
                    anioFin,
                    mesFin,
                    diaFin
            );
            return ResponseEntity.ok(partidos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPartido(@PathVariable Long id) {
        partidoService.eliminarPartido(id);
        return ResponseEntity.noContent().build();
    }
}
