package com.antonriva.backendspring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/conexion")
public class ConexionController {

    private final DataSource dataSource;

    public ConexionController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/refrescar")
    public ResponseEntity<String> refrescarConexion() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(2)) { // Valida la conexión en 2 segundos
                return ResponseEntity.ok("Conexión refrescada exitosamente.");
            } else {
                return ResponseEntity.status(500).body("La conexión no es válida.");
            }
        } catch (SQLException e) {
            return ResponseEntity.status(500).body("Error al refrescar la conexión: " + e.getMessage());
        }
    }
    
    @PostMapping("/cerrar-transaccion")
    public ResponseEntity<String> cerrarTransaccion() {
        try (Connection connection = dataSource.getConnection()) {
            if (!connection.isClosed()) {
                connection.rollback(); // Asegurarse de que la transacción no confirmada se revierta
                System.out.println("Transacción cerrada correctamente.");
                return ResponseEntity.ok("Transacción cerrada correctamente.");
            } else {
                return ResponseEntity.ok("La conexión ya estaba cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión o transacción: " + e.getMessage());
            return ResponseEntity.status(500).body("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}