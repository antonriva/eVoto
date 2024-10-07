package com.antonriva.backendSpring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainControlador {
	
	@GetMapping("/")
	public MiRespuesta index() {
		return new MiRespuesta("Hola mundo!");
	}

	private static class MiRespuesta{
		private String mensaje;
		
		public MiRespuesta(String mensaje) {
			this.mensaje=mensaje;
		}
		
		public String getMensaje() {
			return mensaje;
		}
		
		public void setMensaje(String mensaje) {
			this.mensaje=mensaje;
		}
	}
}
