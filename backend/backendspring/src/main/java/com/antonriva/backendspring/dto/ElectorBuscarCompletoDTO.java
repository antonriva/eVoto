package com.antonriva.backendspring.dto;

import java.time.LocalDate;

public class ElectorBuscarCompletoDTO {
	
    private Long idElector;
    private LocalDate fechaDeInicioElector;
    private LocalDate fechaDeFinElector;
	
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaDeFin;
    
	public ElectorBuscarCompletoDTO(Long idElector, LocalDate fechaDeInicioElector, LocalDate fechaDeFinElector,
			Long id, String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaDeNacimiento,
			LocalDate fechaDeFin) {
		this.idElector = idElector;
		this.fechaDeInicioElector = fechaDeInicioElector;
		this.fechaDeFinElector = fechaDeFinElector;
		this.id = id;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.fechaDeFin = fechaDeFin;
	}

	public Long getIdElector() {
		return idElector;
	}

	public void setIdElector(Long idElector) {
		this.idElector = idElector;
	}

	public LocalDate getFechaDeInicioElector() {
		return fechaDeInicioElector;
	}

	public void setFechaDeInicioElector(LocalDate fechaDeInicioElector) {
		this.fechaDeInicioElector = fechaDeInicioElector;
	}

	public LocalDate getFechaDeFinElector() {
		return fechaDeFinElector;
	}

	public void setFechaDeFinElector(LocalDate fechaDeFinElector) {
		this.fechaDeFinElector = fechaDeFinElector;
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

	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public LocalDate getFechaDeFin() {
		return fechaDeFin;
	}

	public void setFechaDeFin(LocalDate fechaDeFin) {
		this.fechaDeFin = fechaDeFin;
	}
    
    
    

}
