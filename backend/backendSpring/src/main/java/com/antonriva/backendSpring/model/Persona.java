package com.antonriva.backendSpring.model;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class Persona {
	private Long id;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private LocalDate fechaDeInicio;
	private LocalDate fechaDeFin;
	
	public Persona() {
		
	}
	
	public Persona(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaDeInicio) {
		this.id=id;
		this.apellidoMaterno = apellidoMaterno;
		this.apellidoPaterno = apellidoPaterno;
		this.nombre = nombre;
		this.fechaDeInicio = fechaDeInicio;
	}
	

	public Persona(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaDeInicio, LocalDate fechaDeFin) {
		this.id=id;
		this.apellidoMaterno = apellidoMaterno;
		this.apellidoPaterno = apellidoPaterno;
		this.nombre = nombre;
		this.fechaDeInicio = fechaDeInicio;
		this.fechaDeInicio = fechaDeFin;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public LocalDate getFechaDeInicio() {
		return fechaDeInicio;
	}

	public void setFechaDeInicio(LocalDate fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}

	public LocalDate getFechaDeFin() {
		return fechaDeFin;
	}

	public void setFechaDeFin(LocalDate fechaDeFin) {
		this.fechaDeFin = fechaDeFin;
	}

}
