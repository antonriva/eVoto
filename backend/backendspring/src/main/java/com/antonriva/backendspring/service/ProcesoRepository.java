package com.antonriva.backendspring.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antonriva.backendspring.model.Proceso;


@Repository
public interface ProcesoRepository extends JpaRepository<Proceso, Integer> {

    // Aquí puedes agregar otros métodos personalizados, por ejemplo, para buscar por fecha o proceso.
}

