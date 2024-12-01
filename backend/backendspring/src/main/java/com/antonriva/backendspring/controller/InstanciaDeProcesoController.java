package com.antonriva.backendspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.antonriva.backendspring.dto.InstanciaDeProcesoDTO;
import com.antonriva.backendspring.model.InstanciaDeProceso;
import com.antonriva.backendspring.service.InstanciaDeProcesoService;
import com.antonriva.backendspring.specification.InstanciaDeProcesoSpecifications;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/instancia-proceso")
@CrossOrigin(origins = "http://localhost:5173") // Ajusta según tu origen
public class InstanciaDeProcesoController {
    @Autowired
    private InstanciaDeProcesoService instanciaDeProcesoService;

    @GetMapping
    public ResponseEntity<List<InstanciaDeProcesoDTO>> obtenerTodas() {
        return ResponseEntity.ok(instanciaDeProcesoService.obtenerTodas());
    }

    @PostMapping
    public ResponseEntity<InstanciaDeProceso> guardar(@RequestBody InstanciaDeProceso instanciaDeProceso) {
        return ResponseEntity.ok(instanciaDeProcesoService.guardar(instanciaDeProceso));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<InstanciaDeProcesoDTO>> buscarConFiltros(
            @RequestParam(required = false) LocalDate fechaDeInicio,
            @RequestParam(required = false) LocalDate fechaDeFin,
            @RequestParam(required = false) Integer ganadoresNum
    ) {
        Specification<InstanciaDeProceso> spec = Specification
                .where(InstanciaDeProcesoSpecifications.conFechaDeInicio(fechaDeInicio))
                .and(InstanciaDeProcesoSpecifications.conFechaDeFin(fechaDeFin))
                .and(InstanciaDeProcesoSpecifications.conGanadoresNum(ganadoresNum));

        return ResponseEntity.ok(instanciaDeProcesoService.obtenerTodas());
    }
}

