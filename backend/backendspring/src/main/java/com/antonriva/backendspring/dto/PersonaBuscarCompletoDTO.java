package com.antonriva.backendspring.dto;

import java.time.LocalDate;

public class PersonaBuscarCompletoDTO {
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaDeFin;
    private int cantidadDeDomicilios;
    private int cantidadDeHijos;
    private PersonaIdPadresDTO padresDTO;
    
    public PersonaBuscarCompletoDTO (Long id, String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaDeNacimiento, LocalDate fechaDeFin, int cantidadDeDomicilios, int cantidadDeHijos, PersonaIdPadresDTO padresDTO) {
    this.id = id;
    this.nombre = nombre;
    this.apellidoPaterno = apellidoPaterno;
    this.apellidoMaterno = apellidoMaterno;
    this.fechaDeNacimiento = fechaDeNacimiento;
    this.fechaDeFin = fechaDeFin;
    this.cantidadDeDomicilios = cantidadDeDomicilios;
    this.cantidadDeHijos = cantidadDeHijos;
    this.padresDTO = padresDTO;
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

	public int getCantidadDeDomicilios() {
		return cantidadDeDomicilios;
	}

	public void setCantidadDeDomicilios(int cantidadDeDomicilios) {
		this.cantidadDeDomicilios = cantidadDeDomicilios;
	}

	public int getCantidadDeHijos() {
		return cantidadDeHijos;
	}

	public void setCantidadDeHijos(int cantidadDeHijos) {
		this.cantidadDeHijos = cantidadDeHijos;
	}

	public PersonaIdPadresDTO getPadresDTO() {
		return padresDTO;
	}

	public void setPadresDTO(PersonaIdPadresDTO padresDTO) {
		this.padresDTO = padresDTO;
	}

    

}
