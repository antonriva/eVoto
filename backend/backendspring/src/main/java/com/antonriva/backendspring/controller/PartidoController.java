
package com.antonriva.backendspring.controller;

import com.antonriva.backendspring.model.Partido;
import com.antonriva.backendspring.service.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;

    // Obtener todos los partidos
    @GetMapping
    public ResponseEntity<List<Partido>> getAllPartidos() {
        List<Partido> partidos = partidoService.getAllPartidos();
        return new ResponseEntity<>(partidos, HttpStatus.OK);
    }

    // Obtener un partido por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Partido> getPartidoById(@PathVariable int id) {
        Optional<Partido> partido = partidoService.getPartidoById(id);
        return partido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo partido
    @PostMapping
    public ResponseEntity<Partido> createPartido(@RequestBody Partido partido) {
        Partido savedPartido = partidoService.saveOrUpdatePartido(partido);
        return new ResponseEntity<>(savedPartido, HttpStatus.CREATED);
    }

    // Actualizar un partido existente
    @PutMapping("/{id}")
    public ResponseEntity<Partido> updatePartido(@PathVariable int id, @RequestBody Partido partido) {
        if (!partidoService.getPartidoById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        partido.setId(id); // Aseguramos que se mantenga el ID correcto
        Partido updatedPartido = partidoService.saveOrUpdatePartido(partido);
        return ResponseEntity.ok(updatedPartido);
    }

    // Eliminar un partido por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartido(@PathVariable int id) {
        if (!partidoService.getPartidoById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        partidoService.deletePartido(id);
        return ResponseEntity.noContent().build();
    }
}
