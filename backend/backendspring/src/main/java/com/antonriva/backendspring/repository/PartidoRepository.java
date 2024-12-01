package com.antonriva.backendspring.repository;

import com.antonriva.backendspring.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario, como:
    // List<Partido> findByDenominacion(String denominacion);
}

