package com.antonriva.backendspring.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antonriva.backendspring.dto.ElectorBuscarCompletoDTO;
import com.antonriva.backendspring.dto.ElectorBuscarDTO;
import com.antonriva.backendspring.dto.ElectorEditarDTO;
import com.antonriva.backendspring.model.Elector;
import com.antonriva.backendspring.service.ElectorService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/elector")
@CrossOrigin(origins="http://localhost:5173")
public class ElectorController {

    private final ElectorService electorService;

    public ElectorController(ElectorService electorService) {
        this.electorService = electorService;
    }
    
    
    //BUSCAR A DETALLE

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarElectoresConDetalles(
            @RequestParam(required = false) Long idElector,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellidoPaterno,
            @RequestParam(required = false) String apellidoMaterno,
            @RequestParam(required = false) Integer anioNacimiento,
            @RequestParam(required = false) Integer mesNacimiento,
            @RequestParam(required = false) Integer diaNacimiento,
            @RequestParam(required = false) Integer anioFin,
            @RequestParam(required = false) Integer mesFin,
            @RequestParam(required = false) Integer diaFin,
            @RequestParam(required = false) Integer anioInicioElector,
            @RequestParam(required = false) Integer mesInicioElector,
            @RequestParam(required = false) Integer diaInicioElector,
            @RequestParam(required = false) Long entidadFederativa,
            @RequestParam(required = false) Long municipio,
            @RequestParam(required = false) Long localidad,
            @RequestParam(required = false) Long colonia,
            @RequestParam(required = false) Long codigoPostal,
            @RequestParam(required = false) Long tipoDeDomicilioId
    ) {
        try {
            // Llamar al servicio para buscar los electores
            List<ElectorBuscarCompletoDTO> resultados = electorService.buscarElectorConDetalles(
                    idElector,
                    id,
                    nombre,
                    apellidoPaterno,
                    apellidoMaterno,
                    anioNacimiento,
                    mesNacimiento,
                    diaNacimiento,
                    anioFin,
                    mesFin,
                    diaFin,
                    anioInicioElector,
                    mesInicioElector,
                    diaInicioElector,
                    entidadFederativa,
                    municipio,
                    localidad,
                    colonia,
                    codigoPostal,
                    tipoDeDomicilioId
            );

            // Retornar la lista de resultados
            return ResponseEntity.ok(resultados);

        } catch (Exception e) {
            // Manejo de errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar electores con detalles: " + e.getMessage());
        }
    }

    //EDITAR ELECTOR 

    @GetMapping("/{idElector}/editar")
    public ResponseEntity<?> obtenerDatosParaEdicion(@PathVariable Long idElector) {
        try {
            ElectorEditarDTO electorEditarDTO = electorService.obtenerDatosParaEdicion(idElector);
            return ResponseEntity.ok(electorEditarDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los datos del elector para edición.");
        }
    }

    @PutMapping("/{idElector}/editar")
    public ResponseEntity<?> editarElector(@PathVariable Long idElector, @RequestBody @Valid ElectorEditarDTO dto) {
        try {
            System.out.println("Iniciando actualización del elector con ID: " + idElector);
            electorService.editarElector(idElector, dto);
            return ResponseEntity.ok("Elector actualizado correctamente.");
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el elector.");
        }
    }

    
    //REGISTRO DE ELECTOR 
    
    
    //ELIMINAR ELECTOR 
    
    

}