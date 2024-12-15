package com.antonriva.backendspring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antonriva.backendspring.dto.ElectorBuscarDTO;
import com.antonriva.backendspring.model.Elector;
import com.antonriva.backendspring.service.ElectorService;

@RestController
@RequestMapping("/api/elector")
@CrossOrigin(origins="http://localhost:5173")
public class ElectorController {

    private final ElectorService electorService;

    public ElectorController(ElectorService electorService) {
        this.electorService = electorService;
    }


    @GetMapping("/buscar")
    public ResponseEntity<List<ElectorBuscarDTO>> buscarElectores(
            @RequestParam(required = false) Long idDePersona,
            @RequestParam(required = false) Long idDeElector,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellidoPaterno,
            @RequestParam(required = false) String apellidoMaterno,
            @RequestParam(required = false) Integer anioNacimiento,
            @RequestParam(required = false) Integer mesNacimiento,
            @RequestParam(required = false) Integer diaNacimiento,
            @RequestParam(required = false) Integer anioFin,
            @RequestParam(required = false) Integer mesFin,
            @RequestParam(required = false) Integer diaFin,
            @RequestParam(required = false) Long entidadFederativa,
            @RequestParam(required = false) Long municipio,
            @RequestParam(required = false) Long localidad,
            @RequestParam(required = false) Long colonia,
            @RequestParam(required = false) Long codigoPostal,
            @RequestParam(required = false) Long tipoDeDomicilio
    ) {
        try {
            // Llamar al servicio para realizar la búsqueda
            List<ElectorBuscarDTO> resultados = electorService.buscarElectorConDetalles(
                    idDePersona,
                    idDeElector,
                    nombre,
                    apellidoPaterno,
                    apellidoMaterno,
                    anioNacimiento,
                    mesNacimiento,
                    diaNacimiento,
                    anioFin,
                    mesFin,
                    diaFin,
                    entidadFederativa,
                    municipio,
                    localidad,
                    colonia,
                    codigoPostal,
                    tipoDeDomicilio
            );

            // Retornar la lista de DTOs
            return ResponseEntity.ok(resultados);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error al buscar electores con detalles. Por favor intente nuevamente.", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarElector(@PathVariable Long id) {
        electorService.eliminarElector(id);
        return ResponseEntity.noContent().build();
    }
}