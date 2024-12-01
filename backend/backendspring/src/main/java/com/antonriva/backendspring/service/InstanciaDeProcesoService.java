package com.antonriva.backendspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.antonriva.backendspring.dto.InstanciaDeProcesoDTO;
import com.antonriva.backendspring.model.InstanciaDeProceso;
import com.antonriva.backendspring.repository.InstanciaDeProcesoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstanciaDeProcesoService {
    @Autowired
    private InstanciaDeProcesoRepository instanciaDeProcesoRepository;

    public List<InstanciaDeProcesoDTO> obtenerTodas() {
        return instanciaDeProcesoRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    public InstanciaDeProceso guardar(InstanciaDeProceso instanciaDeProceso) {
        return instanciaDeProcesoRepository.save(instanciaDeProceso);
    }

    private InstanciaDeProcesoDTO convertirADTO(InstanciaDeProceso instancia) {
        InstanciaDeProcesoDTO dto = new InstanciaDeProcesoDTO();
        dto.setId(instancia.getId());
        dto.setFechaDeInicio(instancia.getFechaDeInicio());
        dto.setFechaDeFin(instancia.getFechaDeFin());
        dto.setGanadoresNum(instancia.getGanadoresNum());
        dto.setDescripcionNivel(instancia.getNivelProceso().getNivel().getDescripcion());
        dto.setNombreProceso(instancia.getNivelProceso().getProceso().getNombre());
        return dto;
    }
}
