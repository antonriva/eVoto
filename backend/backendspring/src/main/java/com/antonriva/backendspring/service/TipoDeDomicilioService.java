package com.antonriva.backendspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.antonriva.backendspring.repository.TipoDeDomicilioRepository;
import com.antonriva.backendspring.model.TipoDeDomicilio;

import jakarta.transaction.Transactional;

@Service
public class TipoDeDomicilioService {
	
	private final TipoDeDomicilioRepository tipoDeDomicilioRepository;
	
	public TipoDeDomicilioService(TipoDeDomicilioRepository tipoDeDomicilioRepository) {
		this.tipoDeDomicilioRepository = tipoDeDomicilioRepository;
	}
    @Transactional
    public List<TipoDeDomicilio> obtenerTodosLosTiposDeDomicilio() {
        return tipoDeDomicilioRepository.findAll();
    }

}
