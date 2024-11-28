package com.antonriva.backendspring.service;

import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.repository.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PersonaService {
	@Autowired
	private PersonaRepository personaRepository;
	
	public List<Persona> findAll(){
		List<Persona> personas = personaRepository.findAll();
		return personas;
	}
	
}
