package com.antonriva.backendspring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antonriva.backendspring.model.Nivel;
import com.antonriva.backendspring.service.NivelService;

@RequestMapping("/api/niveles")
@RestController
public class NivelController {

    private final NivelService nivelService;

    public NivelController(NivelService nivelService) {
        this.nivelService = nivelService;
    }

    @GetMapping
    public List<Nivel> obtenerTodosLosNiveles() {
        return nivelService.obtenerTodosLosNiveles();
    }
}
