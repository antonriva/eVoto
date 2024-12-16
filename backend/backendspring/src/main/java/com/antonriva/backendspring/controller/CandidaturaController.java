package com.antonriva.backendspring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.antonriva.backendspring.dto.CandidaturaElectorDetalleDTO;
import com.antonriva.backendspring.dto.CandidaturaInstanciaDetalleDTO;
import com.antonriva.backendspring.model.Candidatura;
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
	
	//ESTE ES EL DETALLE QUE SE ABRE DESDE LA VISTA DE CANDIDATURAS CENTRADAS EN EL ELECTOR. RECORDAR QUE LA PRINCIPAL ESTA EN ELECTOR, ESTA ES EXPANDABLE ROW
	
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

    //ESTE ES EL DETALLE QUE SE ABRE DESDE LA INSTANCIA 
    
    @GetMapping("/{idDeInstanciaDeProceso}")
    public ResponseEntity<?> getDetalleCandidaturaInstancia(@PathVariable Long idDeInstanciaDeProceso) {
        System.out.println("Recibida solicitud para la instancia de proceso con ID: " + idDeInstanciaDeProceso);
        try {
            System.out.println("Llamando al servicio para obtener detalles de candidaturas de la instancia...");
            List<CandidaturaInstanciaDetalleDTO> detalles = candidaturaService.getDetalleCandidaturaInstancia(idDeInstanciaDeProceso);
            System.out.println("Detalles obtenidos: " + detalles);
            return ResponseEntity.ok(detalles);
        } catch (IllegalArgumentException e) {
            System.out.println("Error de argumento: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID de la instancia proporcionada no es válido.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al procesar la solicitud.");
        }
    }

    //CREAR CANDIDATURA A PARTIR DE PAGINA DE INSTANCIA DE PROCESO
    //REGISTRO SE HACE A TRAVES DE ID DE PROCESO ELECTORAL 
    //PIDE ID DE PARTIDO (ESTE VA A SER UNA LISTA)
    //DEVUELVE ID DE CANDIDATURA, REGISTRO EXITOSO
    
    @PostMapping("/base")
    public ResponseEntity<?> crearCandidatura(@RequestParam Long idDeProcesoElectoral, @RequestParam Long idDePartido) {
        try {
            if (candidaturaService.existeCandidaturaPorInstanciaYPartido(idDeProcesoElectoral, idDePartido)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe una candidatura para este partido en la instancia de proceso indicada.");
            }

            Long idCandidatura = candidaturaService.crearCandidatura(idDeProcesoElectoral, idDePartido);
            return ResponseEntity.ok("Candidatura creada exitosamente con ID: " + idCandidatura);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }
    
    
    
    //LUEGO SE CREAN LAS RELACIONES ELECTORCANDIDATURA, PUEDEN SER UNA O VARIAS. ESTO SE HACE A TRAVES DEL IDDECANDIDATURA
    //PIDE ID DE ELECTOR PARA CREAR ESTA RELACION
    //LA FECHAHORA DE INICIO TOMA LA DE MOMENTO DE CREACION, MIENTRAS QUE LA FECHA HORA DE FIN LA OBTIENE DE LA INSTANCIADEPROCESO ASOCIADA A LA CANDIDATURA
    //DEBE CONFIRMAR QUE LA FECHA DE INICIO NO SEA POSTERIOR A LA FECHA DE FIN
    //CREA LA RELACION Y DEVUELVE MENSAJE DE CREACION EXITOSA
    
    @PostMapping("/relacion")
    public ResponseEntity<?> crearRelacionesElectorCandidatura(@RequestParam Long idDeCandidatura, @RequestParam Long idDeElector) {
        try {
            if (candidaturaService.existeRelacionElectorCandidatura(idDeCandidatura, idDeElector)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe una relación entre este elector y esta candidatura.");
            }
            String mensaje = candidaturaService.crearRelacionesElectorCandidatura(idDeCandidatura, idDeElector);
            return ResponseEntity.ok(mensaje);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }

    //UN METODO PARA ELIMINAR LA CANDIDATURA,
    //PRIMERO DEBE VERIFICAR QUE NO EXISTA NINGUN VOTO ASOCIADO A ESTA CANDIDATURA, VOTO ES OTRA CLASE QUE TIENE EN SU CONTENIDO UN ID QUE APUNTA A CANDIDATURA, SI YA EXISTE UN VOTO QUE TENGA RELACION CON LA CANDIDATURA YA NO SE PUEDE ELIMINAR
    //LUEGO DEBE VERIFICAR QUE LA FECHAHORADEFIN DE LA INSTANCIADEPROCESO NO HAYA CONCLUIDO (O SEA, SEA ANTERIOR A LA FECHA ACTUAL), SI YA CONCLUYO NO SE PUEDE ELIMINAR
    //FINALMENTE SI NO EXISTE NINGUNA DE ESTAS RELACIONES CRITICAS DEBE ELIMINAR TODAS LAS RELACIONES EXISTENTES ENTRE ELECTOR Y LA CANDIDATURA
    //FINALMENTE, ELIMINA LA CANDIDATURA
    
    @DeleteMapping("/eliminar/{idCandidatura}")
    public ResponseEntity<?> verificarYEliminarCandidatura(@PathVariable Long idCandidatura) {
        try {
            // Obtener la candidatura
            Optional<Candidatura> candidaturaOpt = candidaturaService.obtenerCandidaturaPorId(idCandidatura);
            if (candidaturaOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La candidatura con ID " + idCandidatura + " no existe.");
            }

            Candidatura candidatura = candidaturaOpt.get();

            // Verificar dependencias críticas
            List<String> dependenciasCriticas = candidaturaService.verificarDependenciasCriticas(candidatura);
            if (!dependenciasCriticas.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede eliminar la candidatura. Relacionada con: " + String.join(", ", dependenciasCriticas));
            }

            // Proceder a eliminar la candidatura
            candidaturaService.eliminarCandidatura(idCandidatura);
            return ResponseEntity.ok("Candidatura con ID " + idCandidatura + " eliminada exitosamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }
    
}


