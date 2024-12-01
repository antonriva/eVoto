package com.antonriva.backendspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import com.antonriva.backendspring.model.NivelProceso;
import com.antonriva.backendspring.service.NivelProcesoService;
import com.antonriva.backendspring.specification.NivelProcesoSpecifications;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/nivel-proceso")
@CrossOrigin(origins = "http://localhost:5173") // Ajusta el origen según tu configuración
public class NivelProcesoController {
    @Autowired
    private NivelProcesoService nivelProcesoService;

    @GetMapping
    public ResponseEntity<List<NivelProceso>> obtenerTodos() {
        return ResponseEntity.ok(nivelProcesoService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<NivelProceso> guardar(@RequestBody NivelProceso nivelProceso) {
        return ResponseEntity.ok(nivelProcesoService.guardar(nivelProceso));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<NivelProceso>> buscarConFiltros(
            @RequestParam(required = false) Integer idDeNivel,
            @RequestParam(required = false) Integer idDeProceso,
            @RequestParam(required = false) String descripcionNivel,
            @RequestParam(required = false) String nombreProceso
    ) {
        Specification<NivelProceso> spec = Specification
                .where(NivelProcesoSpecifications.conIdDeNivel(idDeNivel))
                .and(NivelProcesoSpecifications.conIdDeProceso(idDeProceso))
                .and(NivelProcesoSpecifications.conDescripcionNivel(descripcionNivel))
                .and(NivelProcesoSpecifications.conNombreProceso(nombreProceso));

        return ResponseEntity.ok(nivelProcesoService.buscarConFiltros(spec));
    }
}
