package com.antonriva.backendspring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antonriva.backendspring.model.Proceso;
import com.antonriva.backendspring.service.ProcesoService;

@RequestMapping("/api/procesos")
@RestController
@CrossOrigin(origins="http://localhost:5173")
public class ProcesoController {
	

    private final ProcesoService procesoService;

    public ProcesoController(ProcesoService procesoService) {
        this.procesoService = procesoService;
    }

    @GetMapping
    public List<Proceso> obtenerTodosLosProcesos() {
        return procesoService.obtenerTodosLosProcesos();
    }

    @GetMapping("/{id}")
    public Proceso obtenerProcesoPorId(@PathVariable Long id) {
        return procesoService.obtenerProcesoPorId(id);
    }


}
