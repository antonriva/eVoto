package com.antonriva.backendspring.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antonriva.backendspring.dto.PartidoBuscarDetalleDTO;
import com.antonriva.backendspring.dto.PartidoEditarDTO;
import com.antonriva.backendspring.dto.PartidoRegistrarDTO;
import com.antonriva.backendspring.model.Partido;
import com.antonriva.backendspring.service.PartidoService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/partido")
@CrossOrigin(origins="http://localhost:5173")
public class PartidoController {

    
    private final PartidoService partidoService;
    
    public PartidoController(PartidoService partidoService) {
    	this.partidoService = partidoService;
    	
    }

    
    //BUSQUEDA CON DETALLES
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

    //EDICION
    @GetMapping("/{id}/editar")
    public ResponseEntity<?> obtenerDatosParaEdicion(@PathVariable Long id) {
        try {
            PartidoEditarDTO partidoEditarDTO = partidoService.obtenerDatosParaEdicion(id);
            return ResponseEntity.ok(partidoEditarDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los datos del partido.");
        }
    }
    
    @PutMapping("/{id}/editar")
    public ResponseEntity<?> editarPartido(
            @PathVariable Long id,
            @RequestBody @Valid PartidoEditarDTO partidoEditarDTO) {
        try {
            partidoService.editarPartido(id, partidoEditarDTO);
            return ResponseEntity.ok("Partido actualizado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el partido.");
        }
    }

    
    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<?> eliminarPartido(@PathVariable Long id) {
        try {
            partidoService.eliminarPartido(id);
            return ResponseEntity.ok("Partido eliminado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el partido.");
        }
    }
    
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarPartido(@RequestBody @Valid PartidoRegistrarDTO partidoRegistrarDTO) {
        System.out.println("Datos recibidos en el controlador: " + partidoRegistrarDTO);
        try {
            // Verificar si ya existe un partido con la misma denominación o siglas
            Optional<Partido> partidoExistente = partidoService.verificarPartidoExistente(
                    partidoRegistrarDTO.getDenominacion(),
                    partidoRegistrarDTO.getSiglas()
            );

            if (partidoExistente.isPresent()) {
                Partido partido = partidoExistente.get();
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        "No se puede registrar el partido. Ya existe un partido con estas características: ID " + partido.getId() +
                        ", denominación: " + partido.getDenominacion() +
                        ", siglas: " + partido.getSiglas() + "."
                );
            }

            // Registrar el nuevo partido
            Partido nuevoPartido = partidoService.registrarPartido(partidoRegistrarDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    "Partido registrado exitosamente con ID: " + nuevoPartido.getId()
            );

        } catch (IllegalArgumentException e) {
            // Manejo de errores de validación
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            // Manejo de entidades no encontradas
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // Manejo de errores internos
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el partido.");
        }
    }

    
    
    
}
