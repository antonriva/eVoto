package com.antonriva.backendspring.controller;

import com.antonriva.backendspring.model.Persona;
import com.antonriva.backendspring.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api")
@RestController
public class PersonaController {
	@Autowired
	private PersonaService personaService;
	@GetMapping(value = "/persona")
	public List<Persona> getAllPersonas(){
		return personaService.findAll();
	} 
	

}
