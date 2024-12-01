package com.antonriva.backendspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.antonriva.backendspring.model.NivelProceso;
import com.antonriva.backendspring.repository.NivelProcesoRepository;

import java.util.List;

@Service
public class NivelProcesoService {
    @Autowired
    private NivelProcesoRepository nivelProcesoRepository;

    public List<NivelProceso> obtenerTodos() {
        return nivelProcesoRepository.findAll();
    }

    public NivelProceso guardar(NivelProceso nivelProceso) {
        return nivelProcesoRepository.save(nivelProceso);
    }

    public List<NivelProceso> buscarConFiltros(Specification<NivelProceso> spec) {
        return nivelProcesoRepository.findAll(spec);
    }
}
