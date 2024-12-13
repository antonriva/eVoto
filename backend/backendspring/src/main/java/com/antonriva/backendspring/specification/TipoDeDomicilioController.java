package com.antonriva.backendspring.specification;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antonriva.backendspring.model.TipoDeDomicilio;
import com.antonriva.backendspring.service.TipoDeDomicilioService;


@RestController
@RequestMapping("/api/tipoDeDomicilio")
@CrossOrigin(origins="http://localhost:5173")
public class TipoDeDomicilioController {
	
    private final TipoDeDomicilioService tipoDeDomicilioService;
    
    public TipoDeDomicilioController(TipoDeDomicilioService tipoDeDomicilioService) {
    	this.tipoDeDomicilioService = tipoDeDomicilioService;
    }
    
    @GetMapping
    public ResponseEntity<List<TipoDeDomicilio>> obtenerTodosLosTiposDeDomicilio() {
        List<TipoDeDomicilio> tiposDeDomicilio = tipoDeDomicilioService.obtenerTodosLosTiposDeDomicilio();
        return ResponseEntity.ok(tiposDeDomicilio);
    }

}
