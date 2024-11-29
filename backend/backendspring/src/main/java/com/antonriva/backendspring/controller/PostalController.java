package com.antonriva.backendspring.controller;

import com.antonriva.backendspring.model.Postal;
import com.antonriva.backendspring.service.PostalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/postal")
public class PostalController {

    @Autowired
    private PostalService postalService;

    // Obtener todos los códigos postales
    @GetMapping
    public ResponseEntity<List<Postal>> obtenerTodosLosPostales() {
        List<Postal> postales = postalService.obtenerTodosLosPostales();
        return ResponseEntity.ok(postales);
    }

    // Obtener un código postal por ID
    @GetMapping("/{id}")
    public ResponseEntity<Postal> obtenerPostalPorId(@PathVariable int id) {
        Optional<Postal> postal = postalService.obtenerPostalPorId(id);
        return postal.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Buscar códigos postales por ID de colonia
    @GetMapping("/colonia/{coloniaId}")
    public ResponseEntity<List<Postal>> obtenerPostalesPorColoniaId(@PathVariable int coloniaId) {
        List<Postal> postales = postalService.obtenerPostalesPorColoniaId(coloniaId);
        return ResponseEntity.ok(postales);
    }

    // Buscar códigos postales con filtros
    @GetMapping("/buscar")
    public ResponseEntity<List<Postal>> buscarConFiltros(
            @RequestParam(required = false) Integer descripcion,
            @RequestParam(required = false) Integer coloniaId) {
        List<Postal> postales = postalService.buscarConFiltros(descripcion, coloniaId);
        return ResponseEntity.ok(postales);
    }

    // Crear un nuevo código postal
    @PostMapping
    public ResponseEntity<Postal> crearPostal(@RequestBody Postal nuevoPostal) {
        Postal postalGuardado = postalService.registrarPostal(nuevoPostal);
        return ResponseEntity.status(HttpStatus.CREATED).body(postalGuardado);
    }

    // Actualizar un código postal
    @PutMapping("/{id}")
    public ResponseEntity<Postal> actualizarPostal(@PathVariable int id, @RequestBody Postal nuevoPostal) {
        try {
            Postal postalActualizado = postalService.actualizarPostal(id, nuevoPostal);
            return ResponseEntity.ok(postalActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un código postal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPostal(@PathVariable int id) {
        try {
            postalService.eliminarPostal(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
