package com.antonriva.backendspring.dto;

import java.time.LocalDate;

public class PersonaRegistroDTO {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaDeNacimiento;
    private Long entidadFederativaId;
    private Long municipioId;
	
    public PersonaRegistroDTO(String nombre, String apellidoPaterno, String apellidoMaterno,
			LocalDate fechaDeNacimiento, Long entidadFederativaId, Long municipioId) {
		super();
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.entidadFederativaId = entidadFederativaId;
		this.municipioId = municipioId;
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

	public Long getEntidadFederativaId() {
		return entidadFederativaId;
	}

	public void setEntidadFederativaId(Long entidadFederativaId) {
		this.entidadFederativaId = entidadFederativaId;
	}

	public Long getMunicipioId() {
		return municipioId;
	}

	public void setMunicipioId(Long municipioId) {
		this.municipioId = municipioId;
	}
    
	
	

}
